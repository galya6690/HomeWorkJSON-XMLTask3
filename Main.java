package com.gmail.galya6690;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
public class Main extends DefaultHandler {
	private boolean bauthor;
	private String element;
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("document started");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("documentend");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		element = localName;
		bauthor = true;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (bauthor) {
			if (element.equals("CharCode")) {
				System.out.println("CharCode - " + new String(ch, start, length));
				bauthor = false;
			} else if (element.equals("Name")) {
				System.out.println("Name - " + new String(ch, start, length));
				bauthor = false;
			} else if (element.equals("Nominal")) {
				System.out.println("Nominal - " + new String(ch, start, length));
				bauthor = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		spf.setNamespaceAware(true);
		SAXParser saxParser = spf.newSAXParser();
		XMLReader xmlReader = saxParser.getXMLReader();
		xmlReader.setContentHandler(new Main());
		xmlReader.parse("http://www.cbr.ru/scripts/XML_daily.asp");
	}
}