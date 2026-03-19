DATA SEGMENT
	x DD
DATA ENDS
CODE SEGMENT
	 mov eax, 0
	 mov x, eax
	 mov eax, 0
	 push eax
debut_while_33:
	 mov eax, x
	 push eax
	 mov eax, 10
	 pop ebx
	 sub ebx, eax
	 jl vrai_jl_34
	 mov eax, 0
	 jmp fin_jl_34
vrai_jl_34:
	 mov eax, 1
fin_jl_34:
	 sub eax, 0
	 jz fin_while_33
	 pop eax
	 mov eax, x
	 push eax
	 mov eax, 1
	 pop ebx
	 add eax, ebx
	 mov x, eax
	 push eax
	 jmp debut_while_33
fin_while_33:
	 pop eax
	 out eax
CODE ENDS
