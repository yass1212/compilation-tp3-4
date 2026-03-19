DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 1
	 sub eax, 0
	 jz else_13
	 mov eax, 10
	 jmp fin_if_13
else_13:
	 mov eax, 20
fin_if_13:
	 out eax
CODE ENDS
