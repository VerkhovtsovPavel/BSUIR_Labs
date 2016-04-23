LIST	p=16F84			;tell assembler what chip we are using
include "P16F84.inc"		;include the defaults for the chip
__CONFIG _WDT_OFF

cblock	0x20			;start of general purpose registers
    MS_COUNT
    WAIT_COUNTER
    CMD_TEMP			;temp store for 4 bit mode	
    COUNTER
    POSITION
    LINE_FLAG
    W_TEMP
    STATUS_TEMP   
    INT_COUNTER
endc

LCD_PORT	Equ	PORTB
LCD_TRIS	Equ	TRISB
LCD_RS		Equ	0x01			;LCD handshake lines
LCD_RW		Equ	0x02
LCD_E		Equ	0x03
LCD_LINE	Equ	0x00		

org	0x00
	goto Initialise
org	0x04
	movwf	W_TEMP
	swapf	STATUS, W
	movwf	STATUS_TEMP
	
	btfsc	INTCON, T0IF
	call	Timer_signal
	btfsc	INTCON, INTF
	call	Change_position

	swapf	STATUS_TEMP, W
	movwf	STATUS
	swapf	W_TEMP, F
	swapf	W_TEMP, W
	retfie		

Initialise	
	clrf	PORTA
	clrf	PORTB
	clrw

	bsf 	STATUS, RP0	;select bank 1
	movwf	LCD_TRIS
	bcf 	STATUS,	RP0	;select bank 0
	movlw	0x32
	call	Delay		;wait for LCD to settle
	
	movlw	0x02		;Set 4 bit mode
	call	LCD_Cmd
	movlw	0x0E		;Set display character mode
	call	LCD_Cmd
	movlw	0x28		;Set double line mode
	call	LCD_Cmd
	movlw	0x06		;Set display shift
	call	LCD_Cmd		
	movlw	0x0C		;Set display on/off and cursor command
	call	LCD_Cmd
	call	LCD_Clr		;Clear display
	
	bcf	INTCON, GIE
	bsf	STATUS, RP0
	clrw
	movwf	TRISB
	bsf	TRISB, RB0
	clrf	OPTION_REG
	bsf	OPTION_REG, PS2
	bsf	OPTION_REG, PS0
	bcf	STATUS, RP0
	clrf	TMR0
	bsf	INTCON, T0IE
	bsf	INTCON, INTE
	clrf	COUNTER
	clrf	LINE_FLAG
	bsf	INTCON, GIE

Loop	
	nop
	goto	Loop

Output
	call	LCD_Clr
	call	LCD_Position
	movfw	COUNTER
	call	Symbols		;get a character from the text table
	call	LCD_Char
	return
	
LCD_Cmd	
	movwf	CMD_TEMP 		;send upper nibble
	andlw	0xf0			;clear upper 4 bits of W
	movwf	LCD_PORT
	bcf	LCD_PORT, LCD_RS	;RS line to 0
	call	Pulse_e			;Pulse the E line high

	movfw	CMD_TEMP	
	swapf	CMD_TEMP, w		;send lower nibble
	andlw	0xf0			;clear upper 4 bits of W
	movwf	LCD_PORT
	bcf	LCD_PORT, LCD_RS	;RS line to 0
	call	Pulse_e			;Pulse the E line high
	movlw	0x32
	call 	Delay
	return

LCD_Char
	movwf	CMD_TEMP		;send upper nibble
	andlw	0xf0			;clear upper 4 bits of W
	movwf	LCD_PORT
	bsf	LCD_PORT, LCD_RS	;RS line to 1
	call	Pulse_e			;Pulse the E line high

	movfw	CMD_TEMP		;send lower nibble
	swapf	CMD_TEMP, W		
	andlw	0xf0			;clear upper 4 bits of W
	movwf	LCD_PORT
	bsf	LCD_PORT, LCD_RS	;RS line to 1
	call	Pulse_e			;Pulse the E line high
	movlw	0x05
	call 	Delay
	return
	
Change_position
	bcf	INTCON, INTF
	incf	POSITION, F
	movfw	POSITION
	sublw	0x10
	btfsc	STATUS, Z
	call	Change_row
	call	Output
	return

Change_row
	clrf	POSITION
	movfw	LINE_FLAG
	xorlw	0x01
	movwf	LINE_FLAG
	clrf	COUNTER
	clrf	TMR0
	return
	
Timer_signal
	bcf	INTCON, T0IF
	incf	INT_COUNTER
	movfw	INT_COUNTER
	sublw	0x4D
	btfss	STATUS, Z
	return
	clrf	INT_COUNTER
	incf	COUNTER
	movfw	COUNTER
	sublw	0x0A
	btfsc	STATUS, Z
	clrf	COUNTER
	call	Output
	return	
		
LCD_Position
	movfw	POSITION
	btfss	LINE_FLAG, LCD_LINE
	addlw	0x80		;move to 1st row, column W
	btfsc	LINE_FLAG, LCD_LINE
	addlw	0xC0		;move to 2nd row, column W
	call	LCD_Cmd
	return

LCD_Clr
	movlw	0x01			;Clear display
	call	LCD_Cmd
	return
	
Pulse_e		
	bsf	LCD_PORT, LCD_E
	nop
	bcf	LCD_PORT, LCD_E
	return	

Delay	
	movwf	MS_COUNT
Internal_Delay	
	movlw	0xC7			;delay 1mS
	movwf	WAIT_COUNTER
MiliSec_Delay
	decfsz	WAIT_COUNTER, F
	goto	MiliSec_Delay
	decfsz	MS_COUNT ,F
	goto	Internal_Delay
	return

Symbols
	addwf	PCL, f
	retlw	b'00110000'
	retlw	b'00110001'
	retlw	b'00110010'
	retlw	b'00110011'
	retlw	b'00110100'
	retlw	b'00110101'
	retlw	b'00110110'
	retlw	b'00110111'
	retlw	b'00111000'
	retlw	b'00111001'
end  