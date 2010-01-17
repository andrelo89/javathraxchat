package server;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.common.SimpleByteBufferAllocator;
import org.apache.mina.filter.LoggingFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;

public class NBSserver {
	public static final int LISTEN_PORT = 3333;
	public static final String TEXT_ENCODING = "ISO-8859-1";
//	public static final String TEXT_ENCODING = "UTF-8";
	public static void main(String[] args) throws IOException {
		
		Persister p = null;
		try {
			XMLDecoder d = null;
			d = new XMLDecoder(new BufferedInputStream(new FileInputStream(
					"database.xml")));
			p = (Persister) d.readObject();
			d.close();
		} catch (FileNotFoundException e) {
			System.out.println("database file not found. using default rooms and admin password");
			ArrayList<String> rooms = new ArrayList<String>();
			rooms.add("A");
			rooms.add("B");
			rooms.add("C");
			p = new Persister();
			p.setCurrentRooms(rooms);
			p.setAdminPassword("admin");
		}

        // The following two lines change the default buffer type to 'heap',
        // which yields better performance.c
        ByteBuffer.setUseDirectBuffers(false);
        ByteBuffer.setAllocator(new SimpleByteBufferAllocator());

        IoAcceptor acceptor = new SocketAcceptor();
        SocketAcceptorConfig cfg = new SocketAcceptorConfig();
        cfg.getFilterChain().addLast( "logger", new LoggingFilter() );
        cfg.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new MyCodecFactory( Charset.forName( TEXT_ENCODING ))));
        acceptor.bind( new InetSocketAddress(LISTEN_PORT), new ClientsHandler(p), cfg);
    }
}
