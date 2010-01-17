package server;

import java.nio.charset.Charset;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

public class MyCodecFactory implements ProtocolCodecFactory {
	private final TextLineEncoder encoder;

	private final TextLineDecoder decoder;

	public MyCodecFactory() {
		this(Charset.defaultCharset());
	}

	public MyCodecFactory(Charset charset) {
		encoder = new TextLineEncoder(charset, LineDelimiter.AUTO);
		decoder = new TextLineDecoder(charset, LineDelimiter.AUTO);
	}

	public ProtocolEncoder getEncoder() {
		return encoder;
	}

	public ProtocolDecoder getDecoder() {
		return decoder;
	}

	public int getEncoderMaxLineLength() {
		return encoder.getMaxLineLength();
	}

	public void setEncoderMaxLineLength(int maxLineLength) {
		encoder.setMaxLineLength(maxLineLength);
	}

	public int getDecoderMaxLineLength() {
		return decoder.getMaxLineLength();
	}

	public void setDecoderMaxLineLength(int maxLineLength) {
		decoder.setMaxLineLength(maxLineLength);
	}
}
