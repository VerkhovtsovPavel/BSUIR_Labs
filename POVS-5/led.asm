LIST        p=16F84A     ; Установка типа микроконтроллера
#include <p16f84.inc>    
__CONFIG _WDT_OFF
cblock  10h  ; Блок РОН
	COUNTER
	INT_COUNTER
	SCREEN
	W_TEMP
	STATUS_TEMP
endc
	

org 0x00
	goto	Start
org 0x04
	movwf	W_TEMP
	swapf	STATUS, W
	btfsc	INTCON, T0IF
	goto	Pre_timer_signal
	btfsc	INTCON, INTF
	goto Button_pushed
Restore_status_w
	swapf	STATUS_TEMP, W
	movwf	STATUS
	swapf	W_TEMP, F
	swapf	W_TEMP, W
	retfie
	
Output
	movfw	COUNTER
	call	Display_table
	movwf	PORTB
	
	movfw	SCREEN
	movwf	PORTA
	return

Start
	bcf	INTCON, GIE
	bsf	STATUS, RP0
	clrw
	movwf	TRISA
	movwf	TRISB
	bsf	TRISB, RB0
	clrf	OPTION_REG
	bsf	OPTION_REG, PS2
	bsf	OPTION_REG, PS0
	bcf	STATUS, RP0
	movwf	PORTA
	movwf	PORTB
	clrf	TMR0
	bsf	INTCON, T0IE
	bsf	INTCON, INTE
	clrf	COUNTER
	movlw	0x01
	movwf	SCREEN
	call	Output
	bsf	INTCON, GIE
Loop
	nop
	goto	Loop
	
Button_pushed
	bcf	INTCON, INTF
	rlf	SCREEN
	movfw	SCREEN
	sublw	0x10
	btfss	STATUS, Z
	goto	Normal_work
	movlw	0x01
	movwf	SCREEN
	clrf	COUNTER
Normal_work	
	call	Output
	goto	Restore_status_w

Pre_timer_signal
	bcf	INTCON, T0IF
	incf	INT_COUNTER
	movfw	INT_COUNTER
	sublw	0x4D
	btfss	STATUS, Z
	goto	Restore_status_w
	clrf	INT_COUNTER
	goto	Timer_signal
Timer_signal
	incf	COUNTER
	movfw	COUNTER
	sublw	0x0A
	btfsc	STATUS, Z
	clrf	COUNTER
	call	Output
	goto	Restore_status_w
	
Display_table
	addwf	PCL, F
	retlw	b'10000001' ;0
	retlw	b'11110011' ;1
	retlw	b'01001001' ;2
	retlw	b'01100001' ;3
	retlw	b'00110011' ;4
	retlw	b'00100101' ;5
	retlw	b'00000101' ;6
	retlw	b'11110001' ;7
	retlw	b'00000001' ;8
	retlw	b'00100001' ;9
end