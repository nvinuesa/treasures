package fr.carbonit.interview.treasures;


import fr.carbonit.interview.treasures.model.*;
import fr.carbonit.interview.treasures.model.Map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class App {


	public static void main(String[] args) {
		LogicFactory factory = new LogicFactory();
		Map map = new Map(factory);
		File f = new File(args[0]);
		try {
			map.init(f);
		} catch (IOException e) {
			System.out.println(e);
		}


		File af = new File(args[1]);
		try {
			BufferedReader br = new BufferedReader(new FileReader(af));
			String line = br.readLine();
			if (line.isEmpty()) {
				throw new  IllegalArgumentException("Incorrect file format, first line of adventurer file cant be empty");
			}
			new Thread(new Adventurer(factory, line, map)).start();
			while ((line = br.readLine()) != null) {
				new Thread(new Adventurer(factory, line, map)).start();
			}
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}
