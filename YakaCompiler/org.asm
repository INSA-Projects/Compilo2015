
	; entete
	extrn lirent:proc, ecrent:proc
	extrn ecrbool:proc
	extrn ecrch:proc, ligsuiv:proc
.model SMALL
.586
.CODE
debut :
	STARTUPCODE

;ouvbloc 2
enter 2,0

	; ouvrePrinc 4
	mov bp,sp
	sub sp,4

	; lireEnt -2
	lea dx,[bp-2]
	push dx
	call lirent

	; aLaligne
	call ligsuiv

	; lireEnt -4
	lea dx,[bp-4]
	push dx
	call lirent

	; aLaligne
	call ligsuiv

	; iload -2
	push word ptr [bp-2]

	; iload -4
	push word ptr [bp-4]

	;isup
	pop bx
	pop ax
	cmp ax, bx
	jle $+6
	push -1
	jmp $+4
	push 0


;iffaux SINON1
	pop ax
	cmp ax,0
	je SINON1

	; iload -2
	push word ptr [bp-2]

;goto FSI1
	goto FSI1

SINON1 :

	; iload -4
	push word ptr [bp-4]

FSI1

;ouvbloc 4
enter 4,0

	; ouvrePrinc 8
	mov bp,sp
	sub sp,8

	; iload 0
	push word ptr [bp0]

	; istore -6
	pop ax
	mov word ptr [bp-6],ax

	; iload -6
	push word ptr [bp-6]

	; ecrireEnt
	call ecrent

