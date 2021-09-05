package uk.ac.glasgow.jp2;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Country implements Comparable<Country> {
	private String name;
	private int population; // In millions

	public Country(String name, int population) {
		this.name = name;
		this.population = population;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @return the population
	 */
	public int getPopulation() {
		return population;
	}

	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, population);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Country)) {
			return false;
		}
		Country other = (Country) obj;
		return Objects.equals(name, other.name) && population == other.population;
	}

	@Override
	public int compareTo(Country o) {
		return Integer.compare(o.population, this.population);
	}
	
	public static void main(String[] args) throws IOException {
		List<Country> countries = new ArrayList<>();
		
		countries.add (new Country ("USA", 331));
		countries.add (new Country ("Scotland", 5));
		countries.add (new Country ("Finland", 5));
		countries.add (new Country ("China", 1439));
		countries.add (new Country ("Canada", 37));
		
		// System.out.println(countries);
		Collections.sort(countries);
		// System.out.println(countries);
		
		Path countryFile = Paths.get("countries.txt");
		
		if (!Files.exists(countryFile)) {
			Files.createFile(countryFile);
		}
		
		PrintWriter pw = new PrintWriter(Files.newBufferedWriter(countryFile));
		
		for (Country c : countries) {
			pw.println(c.getName() + ";" + c.getPopulation());
		}
		
		pw.close();
		
		List<String> lines = Files.readAllLines(countryFile);
		System.out.println(lines);
		
		List<Country> newCountries = new ArrayList<>();
		for (String line : lines) {
			String[] values = line.split(";");
			System.out.println(Arrays.toString(values));
			newCountries.add(new Country(values[0], Integer.valueOf(values[1])));
		}
		
		System.out.println("Original: " + countries);
		System.out.println("New: " + newCountries);
	}

}
