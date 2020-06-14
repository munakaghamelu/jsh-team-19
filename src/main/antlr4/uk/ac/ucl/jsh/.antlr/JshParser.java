// Generated from /workspaces/jsh-team-19/src/main/antlr4/uk/ac/ucl/jsh/Jsh.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JshParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PIPE=1, SEMICOL=2, RDRLEFT=3, RDRRIGHT=4, SINGLEQ=5, BACKQ=6, DOUBLEQ=7, 
		SINGLESTRING=8, DOUBLESTRING=9, BACKSTRING=10, SPACE=11, NEWLINE=12, NONKEY=13;
	public static final int
		RULE_r = 0, RULE_command = 1, RULE_pipe = 2, RULE_call = 3, RULE_argument = 4, 
		RULE_redirection = 5, RULE_quoted = 6, RULE_single = 7, RULE_doubleq = 8, 
		RULE_back = 9, RULE_unquoted = 10;
	public static final String[] ruleNames = {
		"r", "command", "pipe", "call", "argument", "redirection", "quoted", "single", 
		"doubleq", "back", "unquoted"
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

	@Override
	public String getGrammarFileName() { return "Jsh.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JshParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public RContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_r; }
	}

	public final RContext r() throws RecognitionException {
		RContext _localctx = new RContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_r);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
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
		public TerminalNode SEMICOL() { return getToken(JshParser.SEMICOL, 0); }
		public List<TerminalNode> SPACE() { return getTokens(JshParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JshParser.SPACE, i);
		}
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				_localctx = new PipeCommandContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(25);
				pipe(0);
				}
				break;
			case 2:
				{
				_localctx = new CallCommandContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				call();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new SemiCommandContext(new CommandContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_command);
					setState(29);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(33);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(30);
						match(SPACE);
						}
						}
						setState(35);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(36);
					match(SEMICOL);
					setState(40);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(37);
							match(SPACE);
							}
							} 
						}
						setState(42);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
					}
					setState(43);
					command(3);
					}
					} 
				}
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		public TerminalNode PIPE() { return getToken(JshParser.PIPE, 0); }
		public List<TerminalNode> SPACE() { return getTokens(JshParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JshParser.SPACE, i);
		}
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(50);
			call();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(51);
				match(SPACE);
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57);
			match(PIPE);
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(58);
					match(SPACE);
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(64);
			call();
			}
			_ctx.stop = _input.LT(-1);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new PipeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_pipe);
					setState(66);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==SPACE) {
						{
						{
						setState(67);
						match(SPACE);
						}
						}
						setState(72);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(73);
					match(PIPE);
					setState(77);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(74);
							match(SPACE);
							}
							} 
						}
						setState(79);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
					}
					setState(80);
					call();
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public BackContext back() {
			return getRuleContext(BackContext.class,0);
		}
		public List<RedirectionContext> redirection() {
			return getRuleContexts(RedirectionContext.class);
		}
		public RedirectionContext redirection(int i) {
			return getRuleContext(RedirectionContext.class,i);
		}
		public List<TerminalNode> SPACE() { return getTokens(JshParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JshParser.SPACE, i);
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
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==RDRLEFT || _la==RDRRIGHT) {
				{
				{
				setState(86);
				redirection();
				}
				}
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(92);
				match(SPACE);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(100);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NONKEY:
				{
				setState(98);
				unquoted();
				}
				break;
			case BACKSTRING:
				{
				setState(99);
				back();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(116);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(114);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						setState(103); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(102);
							match(SPACE);
							}
							}
							setState(105); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==SPACE );
						setState(107);
						redirection();
						}
						break;
					case 2:
						{
						setState(109); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(108);
							match(SPACE);
							}
							}
							setState(111); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==SPACE );
						setState(113);
						argument();
						}
						break;
					}
					} 
				}
				setState(118);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
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
		public List<QuotedContext> quoted() {
			return getRuleContexts(QuotedContext.class);
		}
		public QuotedContext quoted(int i) {
			return getRuleContext(QuotedContext.class,i);
		}
		public List<UnquotedContext> unquoted() {
			return getRuleContexts(UnquotedContext.class);
		}
		public UnquotedContext unquoted(int i) {
			return getRuleContext(UnquotedContext.class,i);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(121); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(121);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SINGLESTRING:
					case DOUBLESTRING:
					case BACKSTRING:
						{
						setState(119);
						quoted();
						}
						break;
					case NONKEY:
						{
						setState(120);
						unquoted();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(123); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public TerminalNode RDRLEFT() { return getToken(JshParser.RDRLEFT, 0); }
		public ArgumentContext argument() {
			return getRuleContext(ArgumentContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(JshParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JshParser.SPACE, i);
		}
		public TerminalNode RDRRIGHT() { return getToken(JshParser.RDRRIGHT, 0); }
		public RedirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_redirection; }
	}

	public final RedirectionContext redirection() throws RecognitionException {
		RedirectionContext _localctx = new RedirectionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_redirection);
		int _la;
		try {
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case RDRLEFT:
				enterOuterAlt(_localctx, 1);
				{
				setState(125);
				match(RDRLEFT);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(126);
					match(SPACE);
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				argument();
				}
				break;
			case RDRRIGHT:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(RDRRIGHT);
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==SPACE) {
					{
					{
					setState(134);
					match(SPACE);
					}
					}
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(140);
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
		public BackContext back() {
			return getRuleContext(BackContext.class,0);
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
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SINGLESTRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(143);
				single();
				}
				break;
			case BACKSTRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(144);
				back();
				}
				break;
			case DOUBLESTRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(145);
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
		public TerminalNode SINGLESTRING() { return getToken(JshParser.SINGLESTRING, 0); }
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
			setState(148);
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
		public TerminalNode DOUBLESTRING() { return getToken(JshParser.DOUBLESTRING, 0); }
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
			setState(150);
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

	public static class BackContext extends ParserRuleContext {
		public TerminalNode BACKSTRING() { return getToken(JshParser.BACKSTRING, 0); }
		public BackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_back; }
	}

	public final BackContext back() throws RecognitionException {
		BackContext _localctx = new BackContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_back);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(BACKSTRING);
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
		public TerminalNode NONKEY() { return getToken(JshParser.NONKEY, 0); }
		public UnquotedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unquoted; }
	}

	public final UnquotedContext unquoted() throws RecognitionException {
		UnquotedContext _localctx = new UnquotedContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_unquoted);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17\u009f\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\3\3\3\3\3\5\3\36\n\3\3\3\3\3\7\3\"\n\3\f\3\16"+
		"\3%\13\3\3\3\3\3\7\3)\n\3\f\3\16\3,\13\3\3\3\7\3/\n\3\f\3\16\3\62\13\3"+
		"\3\4\3\4\3\4\7\4\67\n\4\f\4\16\4:\13\4\3\4\3\4\7\4>\n\4\f\4\16\4A\13\4"+
		"\3\4\3\4\3\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\3\4\7\4N\n\4\f\4\16\4Q\13"+
		"\4\3\4\7\4T\n\4\f\4\16\4W\13\4\3\5\7\5Z\n\5\f\5\16\5]\13\5\3\5\7\5`\n"+
		"\5\f\5\16\5c\13\5\3\5\3\5\5\5g\n\5\3\5\6\5j\n\5\r\5\16\5k\3\5\3\5\6\5"+
		"p\n\5\r\5\16\5q\3\5\7\5u\n\5\f\5\16\5x\13\5\3\6\3\6\6\6|\n\6\r\6\16\6"+
		"}\3\7\3\7\7\7\u0082\n\7\f\7\16\7\u0085\13\7\3\7\3\7\3\7\7\7\u008a\n\7"+
		"\f\7\16\7\u008d\13\7\3\7\5\7\u0090\n\7\3\b\3\b\3\b\5\b\u0095\n\b\3\t\3"+
		"\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\2\4\4\6\r\2\4\6\b\n\f\16\20\22\24\26"+
		"\2\2\2\u00aa\2\30\3\2\2\2\4\35\3\2\2\2\6\63\3\2\2\2\b[\3\2\2\2\n{\3\2"+
		"\2\2\f\u008f\3\2\2\2\16\u0094\3\2\2\2\20\u0096\3\2\2\2\22\u0098\3\2\2"+
		"\2\24\u009a\3\2\2\2\26\u009c\3\2\2\2\30\31\5\4\3\2\31\3\3\2\2\2\32\33"+
		"\b\3\1\2\33\36\5\6\4\2\34\36\5\b\5\2\35\32\3\2\2\2\35\34\3\2\2\2\36\60"+
		"\3\2\2\2\37#\f\4\2\2 \"\7\r\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2"+
		"\2$&\3\2\2\2%#\3\2\2\2&*\7\4\2\2\')\7\r\2\2(\'\3\2\2\2),\3\2\2\2*(\3\2"+
		"\2\2*+\3\2\2\2+-\3\2\2\2,*\3\2\2\2-/\5\4\3\5.\37\3\2\2\2/\62\3\2\2\2\60"+
		".\3\2\2\2\60\61\3\2\2\2\61\5\3\2\2\2\62\60\3\2\2\2\63\64\b\4\1\2\648\5"+
		"\b\5\2\65\67\7\r\2\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29"+
		";\3\2\2\2:8\3\2\2\2;?\7\3\2\2<>\7\r\2\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2"+
		"?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\5\b\5\2CU\3\2\2\2DH\f\3\2\2EG\7\r\2\2"+
		"FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KO\7\3\2\2"+
		"LN\7\r\2\2ML\3\2\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2"+
		"RT\5\b\5\2SD\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V\7\3\2\2\2WU\3\2\2"+
		"\2XZ\5\f\7\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\a\3\2\2\2][\3\2"+
		"\2\2^`\7\r\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bf\3\2\2\2ca\3\2"+
		"\2\2dg\5\26\f\2eg\5\24\13\2fd\3\2\2\2fe\3\2\2\2gv\3\2\2\2hj\7\r\2\2ih"+
		"\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mu\5\f\7\2np\7\r\2\2o"+
		"n\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2rs\3\2\2\2su\5\n\6\2ti\3\2\2\2"+
		"to\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2w\t\3\2\2\2xv\3\2\2\2y|\5\16\b"+
		"\2z|\5\26\f\2{y\3\2\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\13\3"+
		"\2\2\2\177\u0083\7\5\2\2\u0080\u0082\7\r\2\2\u0081\u0080\3\2\2\2\u0082"+
		"\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0086\u0090\5\n\6\2\u0087\u008b\7\6\2\2\u0088"+
		"\u008a\7\r\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e"+
		"\u0090\5\n\6\2\u008f\177\3\2\2\2\u008f\u0087\3\2\2\2\u0090\r\3\2\2\2\u0091"+
		"\u0095\5\20\t\2\u0092\u0095\5\24\13\2\u0093\u0095\5\22\n\2\u0094\u0091"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\17\3\2\2\2\u0096"+
		"\u0097\7\n\2\2\u0097\21\3\2\2\2\u0098\u0099\7\13\2\2\u0099\23\3\2\2\2"+
		"\u009a\u009b\7\f\2\2\u009b\25\3\2\2\2\u009c\u009d\7\17\2\2\u009d\27\3"+
		"\2\2\2\30\35#*\608?HOU[afkqtv{}\u0083\u008b\u008f\u0094";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}