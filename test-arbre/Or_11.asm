DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 1
	 sub eax, 0
	 jnz vrai_or_29
	 mov eax, 1
	 sub eax, 0
	 jnz vrai_or_29
	 mov eax, 0
	 jmp fin_or_29
vrai_or_29:
	 mov eax, 1
fin_or_29:
	 out eax
CODE ENDS
