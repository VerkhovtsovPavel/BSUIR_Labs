LIST        p=16F84A     ; Установка типа микроконтроллера
#include <p16f84.inc>    

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
	movfw	R1
	movwf	R2	; Помещаем значение в Reg2
	rlf	R2, 1	; Сдвиг 
	movfw	R2      ; Помещаем значение из Reg2 в W
	andlw	0Eh	; Накладываем маску (для RA1-RA3)  
	movwf	PORTA	; Записываем результат в PORTA

	movfw	R1
	movwf	R2	; Помещаем значение в Reg2
	rrf	R2, 1	; Сдвиг
	movfw	R2	; Помещаем значение из Reg2 в W
	andlw	3Ch	; Накладываем маску (для RB2-RB5)
	movwf	PORTB	; Записываем результат в PORTB
	return

Start
	bsf	STATUS,RP0
	clrw
	movwf	TRISA	 ; Очистка регистров TRISA и TRISB (устанавливает порты для работы на Output)
	movwf	TRISB
	bcf	STATUS,RP0
	clrf	R1
Loop
	call	Output
	call	Wait
	incf	R1
	goto	Loop
end