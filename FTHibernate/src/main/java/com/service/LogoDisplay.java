package com.service;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import com.dao.FilesDAO;
import com.entity.Logo;

public class LogoDisplay {

	private LogoDisplay() {
	}

	public static void logoDisplay() {
		try {
			Logo file = FilesDAO.getFilesDaoObject().get(1);
			java.sql.Clob logo = file.getLogo();
			Reader r = logo.getCharacterStream();
			int temp = 0;
			while ((temp = r.read()) != -1) {
				System.out.print((char) temp);
			}
		} catch (SQLException | IOException e) {
			System.out.println("Oh Oh!! We Couldn't Fetch The Logo (-_-)");
			System.exit(0);
		}
	}
}
