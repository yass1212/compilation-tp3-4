DATA SEGMENT
	x DD
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 mov x, eax
	 mov eax, 3
	 push eax
	 mov eax, x
	 pop ebx
	 sub ebx, eax
	 jl vrai_jl_1
	 mov eax, 0
	 jmp fin_jl_1
vrai_jl_1:
	 mov eax, 1
fin_jl_1:
	 jz else_0
	 mov eax, 1
	 out eax
	 jmp fin_if_0
else_0:
	 mov eax, 0
	 out eax
fin_if_0:
CODE ENDS
