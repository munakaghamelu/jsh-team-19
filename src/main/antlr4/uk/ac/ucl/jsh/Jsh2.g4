grammar Jsh2 ;
r2 : command;

command : 
	pipe 													#pipeCommand
	| command SPACE* SEMICOL SPACE* command					#semiCommand
	| call													#callCommand	
	;
pipe 
	: call SPACE* PIPE SPACE* call 			
	| pipe SPACE* PIPE SPACE* call			
	;


call : redirection* SPACE* unquoted (SPACE+ redirection | SPACE+ argument)*;

argument
	: (quoted | unquoted)+
	;

redirection
	: RDRLEFT SPACE* argument 
	| RDRRIGHT SPACE* argument
	;
quoted 
	: single
	| doubleq
	;

single : SINGLESTRING;
doubleq : DOUBLESTRING;

unquoted : NONKEY;


PIPE		: '|';
SEMICOL		: ';';
RDRLEFT		: '<';
RDRRIGHT	: '>';
SINGLEQ		: '\'';
DOUBLEQ		: '"';

SINGLESTRING: SINGLEQ ~('\'' | '\n')+ SINGLEQ; 
DOUBLESTRING: DOUBLEQ (~('"' | '\n')+ )+ DOUBLEQ; 

SPACE: (' ' | '\t');
NEWLINE: '\n' -> skip;

NONKEY: ~('\'' | '"' | ';' | '|' | ' ')+; 
