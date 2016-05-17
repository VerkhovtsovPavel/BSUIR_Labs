LIST        p=16F84A     ; Установка типа микроконтроллера
#include <p16f84.inc>    
__CONFIG _CP_OFF & _WDT_OFF & _LP_OSC & _PWRTE_ON
cblock  10h  ; Блок РОН
	R1
	R2
endc

org 0
	goto	Start

Wait
	movlw	0xF9
	movwf	R2
WaitLoop
	nop
	decfsz	R2
	goto	WaitLoop
	return

Output
	btfsc	R1, 0
	bsf	PORTB, RB1
	btfsc	R1, 1
	bsf	PORTB, RB2
	btfsc	R1, 2
	bsf	PORTA, RA1
	btfsc	R1, 3
	bsf	PORTA, RA0
	
	btfss	R1, 0
	bcf	PORTB, RB1
	btfss	R1, 1
	bcf	PORTB, RB2
	btfss	R1, 2
	bcf	PORTA, RA1
	btfss	R1, 3
	bcf	PORTA, RA0
	return

Start
	bsf	STATUS, RP0
	clrw
	movwf	TRISA	 ; Очистка регистров TRISA и TRISB (устанавливает порты для работы на Output)
	movwf	TRISB
	bsf	TRISB, RB0
	bcf	STATUS, RP0
	movwf	PORTA
	movwf	PORTB
	clrf	R1
Loop
	btfss	PORTB, RB0
	goto	Button_pushed
	bsf	PORTB, RB5
	clrf	R1
	goto	Loop
	
Button_pushed
	bcf	PORTB, RB5
	call	Output
	incf	R1
	movfw	R1
	sublw	0x0A
	btfsc	STATUS, Z
	clrf	R1
	call	Wait
	goto	Loop
end