package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {
	
	final static WikiFetcher wf = new WikiFetcher();
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
        // some example code to get you started

        ArrayList<String> links = new ArrayList<String>();
        Boolean found = false;

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.fetchWikipedia(url);

		for (int i = 0; i < paragraphs.size(); i++) {
			Element paragraph = paragraphs.get(i);
			Iterable<Node> iter = new WikiNodeIterable(paragraph);
		
			for (Node node : iter) {
				if (node instanceof Element) {
 					String link = node.attr("href");

 					if(link.equals("/wiki/Philosophy")) {
 						found = true;
 					} else {
 						links.add("https://en.wikipedia.org" + link);
 					}
 				}
			}
        }

        if (found) {
 			System.out.println("Found Philosophy!");
        } else {
        	System.out.println("Could not find philosophy.");
        }
	}
}
