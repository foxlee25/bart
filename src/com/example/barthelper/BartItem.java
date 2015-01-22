package com.example.barthelper;
// this class implement the each item of bart item for the xml .
public class BartItem {
		public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbr() {
		return abbr;
	}
	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getHexcolor() {
		return hexcolor;
	}
	public void setHexcolor(String hexcolor) {
		this.hexcolor = hexcolor;
	}
	public String getBikeflag() {
		return bikeflag;
	}
	public void setBikeflag(String bikeflag) {
		this.bikeflag = bikeflag;
	}
	
		private String name ;
		@Override
		public String toString() {
			return "BartItem [name=" + name + ", abbr=" + abbr
					+ ", destination=" + destination + ", abbreviation="
					+ abbreviation + ", minutes=" + minutes + ", platform="
					+ platform + ", direction=" + direction + ", length="
					+ length + ", color=" + color + ", hexcolor=" + hexcolor
					+ ", bikeflag=" + bikeflag + "]";
		}

		private String abbr ;
		private String destination ;
		private String abbreviation ;
		private String minutes ;
		private String platform ;
		private String direction ;
		private String length ;
		private String color ;
		private String hexcolor ;
		private String bikeflag ;
		
}
