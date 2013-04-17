package com.taksmind.karma.tarot;

import java.util.ArrayList;

public class Deck {
	private int draw;
	private final int minimum = 0;
	private final int maximum = 21;
	private ArrayList<Card> deck;
	
	public Deck() {
		deck = new ArrayList<Card>();
		deck.add(new Card("The Fool", "The focus of this card is on faith, hope, trust and contentment, high ideals and the possibility of a brighter tomorrow, choice and personal effort."));
		deck.add(new Card("The Magician", "The focus of this card is on new beginnings, new opportunities to use talents, love urges, finance and the future."));
		deck.add(new Card("The High Priestess", "Your focus is on waiting, learning, trade, finance and negotiations, public activities or public relations"));
		deck.add(new Card("The Empress", "The focus of this card is on feelings, emotions, self expression, socializing, or short and long distance trips."));
		deck.add(new Card("The Emperor", "The focus of this card is on construction and formation, or something or someone will change your present situation or present you with opportunities to enter into a new partnership or relationship"));
		deck.add(new Card("The Hierophant", "The focus of this card is on establishments, organizations, truth and understanding, new opportunities and family structures."));
		deck.add(new Card("The Lovers", "Your focus is on sudden and unpredictable changes, love, friendship, unions and meetings, choice, travel, and personnel property."));
		deck.add(new Card("The Chariot", "Your focus is on introspection, re-evaluation, decisions of passion and confusing issues. You will be re-examining your affairs and assessing your situation."));
		deck.add(new Card("Strength", "The focus of this card is on reaching goals, settlements, legal matters or income."));
		deck.add(new Card("The Hermit", "The focus of this card is on seeking and finding, completing and perfecting and problem solving."));
		deck.add(new Card("Wheel of Fortune", "The focus of this card is on conflicts of interest, unexpected developments, important news that changes or alters your course, also important news and information."));
		deck.add(new Card("Justice", "The focus of this card is on challenging situations in relation to business and finance, family matters that stemmed from the past, new beginnings and results or rewards for persistence and effort."));
		deck.add(new Card("The Hanged Man", "The focus of this card is on your life, values and the way you are thinking."));
		deck.add(new Card("Death", "The focus of this card is on endings and new beginnings in career, partnerships, new opportunities or a new lifestyle."));
		deck.add(new Card("Temperance", "The focus of this card is on things that you thought you had completed, that is old friends, lovers, habits, desires, health, money, or business problems and situations that you thought you dealt with, will arise to test you."));
		deck.add(new Card("The Devil", "The focus of this card is on adverse conditions or negative cycle of events."));
		deck.add(new Card("The Tower", "The focus of this card is on unexpected events or devastating conditions, financial problems, separations and divorce conficlics and loss of faith in one's world."));
		deck.add(new Card("The Star", "The focus of this card is on the futures faith and what could be. You will be hurt or disappointed by harsh words, enforced estrangement and circumstances that block your development for the time being."));
		deck.add(new Card("The Moon", "The focus of this card is on inner disturbances or feelings of disquiet or dread or foreboding. Situations are deceptive or changeable in whatever area is important to you at the moment."));
		deck.add(new Card("The Sun", "The focus of this card is on society and the public, self-image or keeping up with appearances; achievement, accomplishment, marriage unions or mergers."));
		deck.add(new Card("Judgement", "The focus of this card is on new personal relationships, new business ventures or partnerships, beneficial help, improved conditions, changes in habit and purpose."));
		deck.add(new Card("The World", "The focus of this card is on world issues or world shaking events, encounters and gatherings, travel, change, risks and new opportunities, but also on restriction wall or blocks."));
	}
	
	public Card draw() {
		draw = minimum + (int)(Math.random()*maximum);
		return(deck.get(draw));
	}
	
	public Card getByName(String name) {
		for( Card c : deck ) {
			if( c.getName().equalsIgnoreCase(name) ) {
				return(c);
			} 
		}
		return(null);
	}
}
