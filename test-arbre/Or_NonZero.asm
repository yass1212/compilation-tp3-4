DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 4
	 sub eax, 0
	 jnz vrai_or_31
	 mov eax, 0
	 sub eax, 0
	 jnz vrai_or_31
	 mov eax, 0
	 jmp fin_or_31
vrai_or_31:
	 mov eax, 1
fin_or_31:
	 out eax
CODE ENDS
