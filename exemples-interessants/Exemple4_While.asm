DATA SEGMENT
	i DD
DATA ENDS
CODE SEGMENT
	 mov eax, 3
	 mov i, eax
debut_while_0:
	 mov eax, 0
	 push eax
	 mov eax, i
	 pop ebx
	 sub ebx, eax
	 jl vrai_jl_1
	 mov eax, 0
	 jmp fin_jl_1
vrai_jl_1:
	 mov eax, 1
fin_jl_1:
	 sub eax, 0
	 jz fin_while_0
	 mov eax, i
	 out eax
	 mov eax, i
	 push eax
	 mov eax, 1
	 pop ebx
	 sub ebx, eax
	 mov eax, ebx
	 mov i, eax
	 jmp debut_while_0
fin_while_0:
CODE ENDS
