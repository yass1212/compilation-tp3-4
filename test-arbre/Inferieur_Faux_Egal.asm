DATA SEGMENT
DATA ENDS
CODE SEGMENT
	 mov eax, 5
	 push eax
	 mov eax, 5
	 pop ebx
	 sub ebx, eax
	 jl vrai_jl_19
	 mov eax, 0
	 jmp fin_jl_19
vrai_jl_19:
	 mov eax, 1
fin_jl_19:
	 out eax
CODE ENDS
