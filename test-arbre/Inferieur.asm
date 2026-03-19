DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 3
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jl vrai_jl_14
	 mov eax, 0
	 jmp fin_jl_14
vrai_jl_14:
	 mov eax, 1
fin_jl_14:
	 out eax
CODE ENDS
