// Generated from /workspaces/jsh-team-19/src/main/antlr4/uk/ac/ucl/jsh/Jsh.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JshLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PIPE=1, SEMICOL=2, RDRLEFT=3, RDRRIGHT=4, SINGLEQ=5, BACKQ=6, DOUBLEQ=7, 
		SINGLESTRING=8, DOUBLESTRING=9, BACKSTRING=10, SPACE=11, NEWLINE=12, NONKEY=13;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PIPE", "SEMICOL", "RDRLEFT", "RDRRIGHT", "SINGLEQ", "BACKQ", "DOUBLEQ", 
		"SINGLESTRING", "DOUBLESTRING", "BACKSTRING", "SPACE", "NEWLINE", "NONKEY"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'|'", "';'", "'<'", "'>'", "'''", "'`'", "'\"'", null, null, null, 
		null, "'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PIPE", "SEMICOL", "RDRLEFT", "RDRRIGHT", "SINGLEQ", "BACKQ", "DOUBLEQ", 
		"SINGLESTRING", "DOUBLESTRING", "BACKSTRING", "SPACE", "NEWLINE", "NONKEY"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JshLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Jsh.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\17S\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\6\t.\n\t\r\t\16\t/\3\t\3\t\3\n\3\n\6\n\66\n\n"+
		"\r\n\16\n\67\3\n\6\n;\n\n\r\n\16\n<\3\n\3\n\3\13\3\13\6\13C\n\13\r\13"+
		"\16\13D\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3\r\3\16\6\16P\n\16\r\16\16\16Q"+
		"\2\2\17\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\3\2\7\4\2\f\f))\4\2\f\f$$\4\2\f\fbb\4\2\13\13\"\"\b\2\"\"$$))==bb~~\2"+
		"X\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t#\3\2"+
		"\2\2\13%\3\2\2\2\r\'\3\2\2\2\17)\3\2\2\2\21+\3\2\2\2\23\63\3\2\2\2\25"+
		"@\3\2\2\2\27H\3\2\2\2\31J\3\2\2\2\33O\3\2\2\2\35\36\7~\2\2\36\4\3\2\2"+
		"\2\37 \7=\2\2 \6\3\2\2\2!\"\7>\2\2\"\b\3\2\2\2#$\7@\2\2$\n\3\2\2\2%&\7"+
		")\2\2&\f\3\2\2\2\'(\7b\2\2(\16\3\2\2\2)*\7$\2\2*\20\3\2\2\2+-\5\13\6\2"+
		",.\n\2\2\2-,\3\2\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\61\3\2\2\2\61"+
		"\62\5\13\6\2\62\22\3\2\2\2\63:\5\17\b\2\64\66\n\3\2\2\65\64\3\2\2\2\66"+
		"\67\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28;\3\2\2\29;\5\25\13\2:\65\3\2\2"+
		"\2:9\3\2\2\2;<\3\2\2\2<:\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\5\17\b\2?\24\3"+
		"\2\2\2@B\5\r\7\2AC\n\4\2\2BA\3\2\2\2CD\3\2\2\2DB\3\2\2\2DE\3\2\2\2EF\3"+
		"\2\2\2FG\5\r\7\2G\26\3\2\2\2HI\t\5\2\2I\30\3\2\2\2JK\7\f\2\2KL\3\2\2\2"+
		"LM\b\r\2\2M\32\3\2\2\2NP\n\6\2\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2"+
		"\2R\34\3\2\2\2\t\2/\67:<DQ\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}