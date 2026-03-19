DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 0
	 sub eax, 0
	 jnz vrai_or_26
	 mov eax, 0
	 sub eax, 0
	 jnz vrai_or_26
	 mov eax, 0
	 jmp fin_or_26
vrai_or_26:
	 mov eax, 1
fin_or_26:
	 out eax
CODE ENDS
