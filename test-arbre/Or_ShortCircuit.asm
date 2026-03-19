DATA SEGMENT
	y DD
DATA ENDS
CODE SEGMENT
	 mov eax, 10
	 mov y, eax
	 mov eax, 1
	 sub eax, 0
	 jnz vrai_or_32
	 mov eax, 20
	 mov y, eax
	 sub eax, 0
	 jnz vrai_or_32
	 mov eax, 0
	 jmp fin_or_32
vrai_or_32:
	 mov eax, 1
fin_or_32:
	 mov eax, y
	 out eax
CODE ENDS
