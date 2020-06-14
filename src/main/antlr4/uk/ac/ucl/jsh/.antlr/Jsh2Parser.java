// Generated from /workspaces/jsh-team-19/src/main/antlr4/uk/ac/ucl/jsh/Jsh2.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Jsh2Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PIPE=1, SEMICOL=2, RDRLEFT=3, RDRRIGHT=4, SINGLEQ=5, DOUBLEQ=6, SINGLESTRING=7, 
		DOUBLESTRING=8, SPACE=9, NEWLINE=10, NONKEY=11;
	public static final int
		RULE_r2 = 0, RULE_command = 1, RULE_pipe = 2, RULE_call = 3, RULE_argument = 4, 
		RULE_redirection = 5, RULE_quoted = 6, RULE_single = 7, RULE_doubleq = 8, 
		RULE_unquoted = 9;
	public static final String[] ruleNames = {
		"r2", "command", "pipe", "call", "argument", "redirection", "quoted", 
		"single", "doubleq", "unquoted"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'|'", "';'", "'<'", "'>'", "'''", "'\"'", null, null, null, "'\n'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PIPE", "SEMICOL", "RDRLEFT", "RDRRIGHT", "SINGLEQ", "DOUBLEQ", 
		"SINGLESTRING", "DOUBLESTRING", "SPACE", "NEWLINE", "NONKEY"
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

	@Override
	public String getGrammarFileName() { return "Jsh2.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Jsh2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class R2Context extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public R2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r2; }
	}

	public final R2Context r2() throws RecognitionException {
		R2Context _localctx = new R2Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_r2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			command(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
	 
		public CommandContext() { }
		public void copyFrom(CommandContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CallCommandContext extends CommandContext {
		public CallContext call() {
			return getRuleContext(CallContext.class,0);
		}
		public CallCommandContext(CommandContext ctx) { copyFrom(ctx); }
	}
	public static class PipeCommandContext extends CommandContext {
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public PipeCommandContext(CommandContext ctx) { copyFrom(ctx); }
	}
	public static class SemiCommandContext extends CommandContext {
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public TerminalNode SEMICOL() { return getToken(Jsh2Parser.SEMICOL, 0); }
		public SemiCommandContext(CommandContext ctx) { copyFrom(ctx); }
	}

	public final CommandContext command() throws RecognitionException {
		return command(0);
	}

	private CommandContext command(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CommandContext _localctx = new CommandContext(_ctx, _parentState);
		CommandContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_command, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new PipeCommandContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(23);
				pipe(0);
				}
				break;
			case 2:
				{
				_localctx = new CallCommandContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(24);
				call();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SemiCommandContext(new CommandContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_command);
					setState(27);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(28);
					match(SEMICOL);
					setState(29);
					command(3);
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PipeContext extends ParserRuleContext {
		public List<CallContext> call() {
			return getRuleContexts(CallContext.class);
		}
		public CallContext call(int i) {
			return getRuleContext(CallContext.class,i);
		}
		public TerminalNode PIPE() { return getToken(Jsh2Parser.PIPE, 0); }
		public PipeContext pipe() {
			return getRuleContext(PipeContext.class,0);
		}
		public PipeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pipe; }
	}

	public final PipeContext pipe() throws RecognitionException {
		return pipe(0);
	}

	private PipeContext pipe(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PipeContext _localctx = new PipeContext(_ctx, _parentState);
		PipeContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_pipe, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(36);
			call();
			setState(37);
			match(PIPE);
			setState(38);
			call();
			}
			_ctx.stop = _input.LT(-1);
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PipeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pipe);
					setState(40);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(41);
					match(PIPE);
					setState(42);
					call();
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CallContext extends ParserRuleContext {
		public UnquotedContext unquoted() {
			return getRuleContext(UnquotedContext.class,0);
		}
		public List<RedirectionContext> redirection() {
			return getRuleContexts(RedirectionContext.class);
		}
		public RedirectionContext redirection(int i) {
			return getRuleContext(RedirectionContext.class,i);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public CallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_call; }
	}

	public final CallContext call() throws RecognitionException {
		CallContext _localctx = new CallContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_call);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RDRLEFT || _la==RDRRIGHT) {
				{
				{
				setState(48);
				redirection();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			unquoted();
			setState(59);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(57);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case RDRLEFT:
					case RDRRIGHT:
						{
						setState(55);
						redirection();
						}
						break;
					case SINGLESTRING:
					case DOUBLESTRING:
					case NONKEY:
						{
						setState(56);
						argument();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(61);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public QuotedContext quoted() {
			return getRuleContext(QuotedContext.class,0);
		}
		public UnquotedContext unquoted() {
			return getRuleContext(UnquotedContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SINGLESTRING:
			case DOUBLESTRING:
				{
				setState(62);
				quoted();
				}
				break;
			case NONKEY:
				{
				setState(63);
				unquoted();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RedirectionContext extends ParserRuleContext {
		public TerminalNode RDRLEFT() { return getToken(Jsh2Parser.RDRLEFT, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public TerminalNode RDRRIGHT() { return getToken(Jsh2Parser.RDRRIGHT, 0); }
		public RedirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_redirection; }
	}

	public final RedirectionContext redirection() throws RecognitionException {
		RedirectionContext _localctx = new RedirectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_redirection);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RDRLEFT:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(RDRLEFT);
				setState(67);
				argument();
				}
				break;
			case RDRRIGHT:
				enterOuterAlt(_localctx, 2);
				{
				setState(68);
				match(RDRRIGHT);
				setState(69);
				argument();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuotedContext extends ParserRuleContext {
		public SingleContext single() {
			return getRuleContext(SingleContext.class,0);
		}
		public DoubleqContext doubleq() {
			return getRuleContext(DoubleqContext.class,0);
		}
		public QuotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quoted; }
	}

	public final QuotedContext quoted() throws RecognitionException {
		QuotedContext _localctx = new QuotedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_quoted);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SINGLESTRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				single();
				}
				break;
			case DOUBLESTRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				doubleq();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleContext extends ParserRuleContext {
		public TerminalNode SINGLESTRING() { return getToken(Jsh2Parser.SINGLESTRING, 0); }
		public SingleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_single; }
	}

	public final SingleContext single() throws RecognitionException {
		SingleContext _localctx = new SingleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_single);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(SINGLESTRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleqContext extends ParserRuleContext {
		public TerminalNode DOUBLESTRING() { return getToken(Jsh2Parser.DOUBLESTRING, 0); }
		public DoubleqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleq; }
	}

	public final DoubleqContext doubleq() throws RecognitionException {
		DoubleqContext _localctx = new DoubleqContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_doubleq);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(DOUBLESTRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnquotedContext extends ParserRuleContext {
		public TerminalNode NONKEY() { return getToken(Jsh2Parser.NONKEY, 0); }
		public UnquotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unquoted; }
	}

	public final UnquotedContext unquoted() throws RecognitionException {
		UnquotedContext _localctx = new UnquotedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_unquoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(NONKEY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return command_sempred((CommandContext)_localctx, predIndex);
		case 2:
			return pipe_sempred((PipeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean command_sempred(CommandContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean pipe_sempred(PipeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\rU\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\3\3\3\3\3\5\3\34\n\3\3\3\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4.\n\4\f\4\16\4\61\13\4\3\5\7\5\64\n\5"+
		"\f\5\16\5\67\13\5\3\5\3\5\3\5\7\5<\n\5\f\5\16\5?\13\5\3\6\3\6\5\6C\n\6"+
		"\3\7\3\7\3\7\3\7\5\7I\n\7\3\b\3\b\5\bM\n\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\13\2\4\4\6\f\2\4\6\b\n\f\16\20\22\24\2\2\2S\2\26\3\2\2\2\4\33\3\2\2\2"+
		"\6%\3\2\2\2\b\65\3\2\2\2\nB\3\2\2\2\fH\3\2\2\2\16L\3\2\2\2\20N\3\2\2\2"+
		"\22P\3\2\2\2\24R\3\2\2\2\26\27\5\4\3\2\27\3\3\2\2\2\30\31\b\3\1\2\31\34"+
		"\5\6\4\2\32\34\5\b\5\2\33\30\3\2\2\2\33\32\3\2\2\2\34\"\3\2\2\2\35\36"+
		"\f\4\2\2\36\37\7\4\2\2\37!\5\4\3\5 \35\3\2\2\2!$\3\2\2\2\" \3\2\2\2\""+
		"#\3\2\2\2#\5\3\2\2\2$\"\3\2\2\2%&\b\4\1\2&\'\5\b\5\2\'(\7\3\2\2()\5\b"+
		"\5\2)/\3\2\2\2*+\f\3\2\2+,\7\3\2\2,.\5\b\5\2-*\3\2\2\2.\61\3\2\2\2/-\3"+
		"\2\2\2/\60\3\2\2\2\60\7\3\2\2\2\61/\3\2\2\2\62\64\5\f\7\2\63\62\3\2\2"+
		"\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2\2\67\65\3\2\2\2"+
		"8=\5\24\13\29<\5\f\7\2:<\5\n\6\2;9\3\2\2\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2"+
		"\2=>\3\2\2\2>\t\3\2\2\2?=\3\2\2\2@C\5\16\b\2AC\5\24\13\2B@\3\2\2\2BA\3"+
		"\2\2\2C\13\3\2\2\2DE\7\5\2\2EI\5\n\6\2FG\7\6\2\2GI\5\n\6\2HD\3\2\2\2H"+
		"F\3\2\2\2I\r\3\2\2\2JM\5\20\t\2KM\5\22\n\2LJ\3\2\2\2LK\3\2\2\2M\17\3\2"+
		"\2\2NO\7\t\2\2O\21\3\2\2\2PQ\7\n\2\2Q\23\3\2\2\2RS\7\r\2\2S\25\3\2\2\2"+
		"\13\33\"/\65;=BHL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}