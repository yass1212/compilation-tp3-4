DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 7
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jl vrai_jl_20
	 mov eax, 0
	 jmp fin_jl_20
vrai_jl_20:
	 mov eax, 1
fin_jl_20:
	 out eax
CODE ENDS
