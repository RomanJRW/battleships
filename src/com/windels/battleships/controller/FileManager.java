package com.windels.battleships.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

	private Path directory;
	
	public FileManager()	{
		directory = Paths.get("", "src", "SaveFiles").toAbsolutePath();
	}
	
        //NOT CURRENTLY USED
	public String getSaveDirectoryName()	{
		String dirName = directory.toString();
		return dirName;
	}
	
	public List<String> getFilesNamesList()	{
		List<String> fileNamesList = new ArrayList<String>();
		for (int i = 0; i < getAccessibleDirectoryContents().size(); i ++)	{
			fileNamesList.add(getAccessibleDirectoryContents().get(i).getFileName().toString());
		}
		return fileNamesList;
	}
	
	void saveGame(String fileName, GameBoard aGameBoard) throws FileNotFoundException, IOException	{
		String savePath = directory + "/" + fileName;
		saveExistingGameUsingGivenFileName(savePath, aGameBoard);
	}
	
	GameBoard loadGame(String fileName) throws IOException, ClassNotFoundException	{
		String loadPath = directory + "/" + fileName;
		return loadExistingGameAtGivenFileName(loadPath);
	}
	
	//HELPER METHODS BELOW
	
	private GameBoard loadExistingGameAtGivenFileName(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
		FileInputStream fis;
		ObjectInputStream in;
		fis = new FileInputStream(fileName);
		in = new ObjectInputStream(fis);
		GameBoard gameBoard = (GameBoard) in.readObject();
		in.close();
		return gameBoard;
	}

	private void saveExistingGameUsingGivenFileName(String fileName, GameBoard gameBoard) throws FileNotFoundException, IOException {
		FileOutputStream fos;
		ObjectOutputStream out;
		fos = new FileOutputStream(fileName);
		out = new ObjectOutputStream(fos);
		out.writeObject(gameBoard);
		out.close();
	}
	
	private List<Path> getAccessibleDirectoryContents()	{
		List<Path> files = new ArrayList<Path>();
		if (Files.isDirectory(directory))	{
			try (DirectoryStream<Path> filesInDir = Files.newDirectoryStream(directory)) {
			    for (Path file: filesInDir) {
			    	if (Files.isReadable(file) && Files.isWritable(file) && !Files.isHidden(file))	{
			    		files.add(file);
			    	}
			    }
			} catch (IOException ex) {
			    return new ArrayList<Path>();
			}
		}
		return files;			
	}
	
}
