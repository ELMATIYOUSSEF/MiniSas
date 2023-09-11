package rapport_statistique;

import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.color.*;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import controller.BeneficiariesController;
import model.Beneficiaries;
import model.Bibliothecaire;
import model.Book;
import model.Borrow;
import service.BookService;
import service.BorrowService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.kernel.font.PdfFont;

import javax.swing.plaf.IconUIResource;

import static console.Menu.*;


public class statistique {
    BookService bookService = new BookService();
    static BorrowService borrowService ;
    public statistique() {
        this.borrowService =new BorrowService();
    }


    static Date date = new Date();
     static String Name ="Rapport"+date.getTime()+".pdf";
    static String path = "C:\\Users\\YouCode\\Desktop\\rapportStatistique\\"+Name;

    public static void CreatepdfFile()  {


        try {
            // Create a PdfWriter instance to write the PDF file
            PdfWriter pdfWriter = new PdfWriter(path);

            // Create a PdfDocument instance
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            // Create a Document instance to work with the PDF
            Document document = new Document(pdfDocument);

            // Add content to the PDF document
            document.add(new Paragraph("Rapport de Statistiques"));
            document.add(new Paragraph("Statistique 1 : 100"));
            document.add(new Paragraph("Statistique 2 : 200"));
            document.add(new Paragraph("Statistique 3 : 300"));

            // Close the document to save and finalize it
            document.close();

            System.out.println("Rapport de statistiques généré avec succès : " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void showAll(){
        List<Borrow> borrows = new ArrayList<>() ;
        borrows = borrowService.getALL();

        try {
            // Create a PdfWriter instance to write the PDF file
            PdfWriter pdfWriter = new PdfWriter(path);

            // Create a PdfDocument instance
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);

            // Create a Document instance to work with the PDF
            Document document = new Document(pdfDocument);

            Paragraph paragraph = new Paragraph("Rapport de Statistiques");
            Color textColor = new DeviceRgb(255, 0, 0);
            paragraph.setFontColor(textColor);

            paragraph.setTextAlignment(TextAlignment.CENTER);

            document.add(paragraph);
            Paragraph Titre = new Paragraph("Show All Borrowed action : ");
            Color textColorTitre = new DeviceRgb(0,128,0);
            Titre.setFontColor(textColorTitre);
            document.add(Titre);
            // Create a table with 7 columns for the borrowed books' information
            Table table = new Table(7);

            // Set table headers
            table.addCell(new Cell().add(new Paragraph("Book Title")));
            table.addCell(new Cell().add(new Paragraph("Borrow Date")));
            table.addCell(new Cell().add(new Paragraph("Return Date")));
            table.addCell(new Cell().add(new Paragraph("Status")));
            table.addCell(new Cell().add(new Paragraph("ISBN")));
            table.addCell(new Cell().add(new Paragraph("Bib Name")));
            table.addCell(new Cell().add(new Paragraph("Ben Name")));

            // Iterate through the list of borrowed books and add their information to the table
            for (Borrow borrow : borrows) {
                Book book = borrow.getBook();
                Beneficiaries beneficiaries = borrow.getBeneficiaries() ;
                Bibliothecaire bibliothecaire = borrow.getBibliothecaire();
                String Title = book.getTitle();

                // Create a Paragraph to hold the text, then add it to the cell
                Cell cell = new Cell().add(new Paragraph(Title));
                table.addCell(cell);

                cell = new Cell().add(new Paragraph(borrow.getDate_Borrow().toString()));
                table.addCell(cell);

                cell = new Cell().add(new Paragraph(borrow.getDate_return().toString()));
                table.addCell(cell);

                cell = new Cell().add(new Paragraph(borrow.getStatus()));
                table.addCell(cell);

                cell = new Cell().add(new Paragraph(String.valueOf(book.getISBN())));
                table.addCell(cell);

                cell = new Cell().add(new Paragraph(String.valueOf(bibliothecaire.getName())));
                table.addCell(cell);


                cell = new Cell().add(new Paragraph(String.valueOf(beneficiaries.getName())));
                table.addCell(cell);


            }

            // Add the table to the PDF document
            document.add(table);

            getNemBookDisponible(document);

            // Close the document to save and finalize it
            document.close();

            System.out.println("PDF report generated successfully: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getNemBookDisponible(Document document) throws FileNotFoundException {
        List<Book> books;
        books = borrowService.getAllBookDisponible();

            Paragraph Titre = new Paragraph("Show All Books disponible : ");
            Color textColorTitre = new DeviceRgb(0,128,0);
            Titre.setFontColor(textColorTitre);
            document.add(Titre);
            // Create a table with 7 columns for the borrowed books' information
            Table table2 = new Table(5);

            // Set table headers
            table2.addCell(new Cell().add(new Paragraph("Isbn ")));
            table2.addCell(new Cell().add(new Paragraph("Title")));
            table2.addCell(new Cell().add(new Paragraph("Auteur")));
            table2.addCell(new Cell().add(new Paragraph("Quantite")));
            table2.addCell(new Cell().add(new Paragraph("Status")));

            // Iterate through the list of borrowed books and add their information to the table
            for (Book book : books) {

                // Create a Paragraph to hold the text, then add it to the cell
                Cell cell = new Cell().add(new Paragraph(String.valueOf(book.getISBN())));
                table2.addCell(cell);

                cell = new Cell().add(new Paragraph(book.getTitle()));
                table2.addCell(cell);

                cell = new Cell().add(new Paragraph(book.getAuthor()));
                table2.addCell(cell);

                cell = new Cell().add(new Paragraph(String.valueOf(book.getQuantity())));
                table2.addCell(cell);

                cell = new Cell().add(new Paragraph(book.getStatus()));
                table2.addCell(cell);

            }

            // Add the table to the PDF document
            document.add(table2);



    }
    public  void MenuStatistique(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.print("\u001B[32m");
            clearScreen();
            printHeader();
            System.out.println("\t <=========== Statistique  ===========> ");
            System.out.println("\t01 : Afficher tout les livre ");
            System.out.println("\t02 : Generete File / Afficher tout les livers Emprunter ");
            System.out.println("\t   => Afficher tout les livers Emprunter ");
            System.out.println("\t   => Afficher tout les livers Disponible ");
            System.out.println("\t03 : Retournez à la page d'accueil .");
            System.out.print("\nVotre choix : ");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.print("\u001B[31m");
                System.out.println("\n Choix invalide. Veuillez réessayer.");
                System.out.print("\u001B[32m");
                System.out.print("\nVotre choix : ");
            }

                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        int count =0 ;
                       List<Book> books = bookService.getAll();
                       for(Book book : books){
                           count++;
                       }
                        System.out.println("Numbre of books in this library : " + count);
                        break;
                    case 2:
                        showAll();
                        break;
                    case 3:

                        try {
                            menu();
                        }  catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        System.out.print("\u001B[31m");
                        System.out.println("\n Choix invalide. Veuillez réessayer.");
                        System.out.print("\u001B[0m");
                }

        } while(true);
    }

}
