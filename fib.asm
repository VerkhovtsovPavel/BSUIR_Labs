LIST        p=16F84     ; ????????? ???? ????????????????
#include <p16f84.inc>    

cblock  20h  ; ???? ???
	R1
	R2
	R3
	R4
endc

org 2100h
de 05h ;????? ?????????
org 0
goto Start

EEPROM_WRITE
	bsf STATUS, RP0
	bsf EECON1, WREN
	bcf INTCON,GIE  ; ?????????? ?????? ??????????.
	movlw 055h        ; ????????????
	movwf EECON2      ; ?????????
	movlw 0AAh        ; ??? ??????.
	movwf EECON2      ; ----"----
	bsf   EECON1,WR    ; ----"----
	btfsc EECON1,WR ; ?????????, ??????????? ?? ??????
	goto $-1
	bsf INTCON,GIE ; ???????? ???? ?????????? ?? ????????? ?????? ? EEPROM.
	bcf EECON1,WREN   
	bcf STATUS,RP0  ; ??????? ? ??????? ????.
return

EEPROM_READ
	bsf STATUS, RP0
	bsf EECON1, RD
	bcf STATUS, RP0
return

Start
movlw 00h     ; ????????? ?? ?????? 0 ? EEPROM ???????????? ?? ????-?? ? R1
movwf EEADR
call EEPROM_READ
movf EEDATA,W
movwf R3
movlw 00h
movwf R2
movwf R3
addlw 01h	
movwf R1
movwf R4	
cycle
	movf R1,0
	addwf R2,0
	movwf EEDATA   
	movf R4,0      
	movwf EEADR    
	call EEPROM_WRITE
	incf R4,1
	movf R1,0
	movwf R2
	movf EEDATA,0
	movwf R1
	decfsz R3
goto cycle

end
