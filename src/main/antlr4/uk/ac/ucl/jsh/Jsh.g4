grammar Jsh ; 
r : command ;
command : 
	pipe 													#pipeCommand
	| command SPACE* SEMICOL SPACE* command					#semiCommand
	| call													#callCommand	
	;
pipe 
	: call SPACE* PIPE SPACE* call 			
	| pipe SPACE* PIPE SPACE* call			
	;

call : redirection* SPACE* ( unquoted | back ) (SPACE+ redirection | SPACE+ argument)*;

argument
	: (quoted | unquoted)+
	;

redirection
	: RDRLEFT SPACE* argument 
	| RDRRIGHT SPACE* argument
	;
quoted 
	: single
	| back
	| doubleq
	;

single : SINGLESTRING;
doubleq : DOUBLESTRING; 
back : BACKSTRING;

unquoted : NONKEY;  


PIPE	: '|';
SEMICOL	: ';';
RDRLEFT	: '<';
RDRRIGHT: '>';  
SINGLEQ	: '\'';
BACKQ	: '`';
DOUBLEQ	: '"';



SINGLESTRING: SINGLEQ ~('\'' | '\n')+ SINGLEQ; 
DOUBLESTRING: DOUBLEQ (~('"' | '\n')+ | BACKSTRING)+ DOUBLEQ; 
BACKSTRING: BACKQ ~('`' | '\n')+ BACKQ;


SPACE: (' ' | '\t'); 
NEWLINE: '\n' -> skip;

NONKEY: ~('\'' | '"' | '`' | ';' | '|' | ' ')+; 
