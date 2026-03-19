DATA SEGMENT
	x DD
	estPositif DD
DATA ENDS
CODE SEGMENT
	 in eax
	 mov x, eax
	 mov eax, 0
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
	 sub eax, 0
	 jz else_0
	 mov eax, 1
	 jmp fin_if_0
else_0:
	 mov eax, 0
fin_if_0:
	 mov estPositif, eax
	 mov eax, estPositif
	 out eax
CODE ENDS
