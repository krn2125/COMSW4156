RouteController.java:92
fix:
catch (Exception e) {
return new ResponseEntity<>("Book not found.", HttpStatus.INTERNAL_SERVER_ERROR);
}

Book.java:182
fix:
public String getLanguage() {return language;}

Book.java:267
fix:
Book cmpBook = (Book) obj;

Book.java:114 Book.java:116
fix:
public boolean deleteCopy() {
if (totalCopies > 0 && copiesAvailable > 0) {
totalCopies--;
copiesAvailable--;
return true;
}
return false;
}

Book.java:96
fix:
public boolean hasCopies() {
return copiesAvailable > 0;
}

Book.java:120
fix:
public void addCopy() {
totalCopies++;
copiesAvailable++;
}

Book.java:134
fix:
amountOfTimesCheckedOut++;

Book.java:153
fix:
if (!returnDates.isEmpty()) { ... }

RouteController.java:71
Old:
return new ResponseEntity<>(mockApiService.getBooks(), HttpStatus.OK);
fixed:
return new ResponseEntity<>(availableBooks, HttpStatus.OK);

fix:
RouteController.java:90
StringBuilder currBookId = new StringBuilder(book.getId()); is unused

RouteController.java:96
return new ResponseEntity<>("Book not found.", HttpStatus.I_AM_A_TEAPOT);
fixed:
return new ResponseEntity<>("Book not found.", HttpStatus.NOT_FOUND);

MockApiService.java:63
this.books = this.books;
fixed:
this.books = tmpBooks;

RouteController.java:72
} catch (Exception e) {
System.err.println(e);
fix: delete

PMD LooseCoupling Bugs:
(Book.java, MockApiService.java)
Replaced "ArrayList" declarations with "List<> = ArrayList"

Books.java:24:
private List<String> bookmarks;
Unused, commented out

Ignoring PMD "AtLeastOneConstructor" error--Test classes do not need constructors

Ignoring PMD "UseUtilityClass" error--was told not to modify IndividualProjectApplication

Ignoring PMD "ShortClassName"/"ShortVariable" for Book -- was given that class by instructor

PMD 50+ "Value could be final" - changed all values to final

Book.java.20: PMD "LongVariable" Error
private int amountOfTimesCheckedOut;
Ignored, in untouchable files.

MockApiService.java:55: PMD Error, UseDiamondOperator
books = mapper.readValue(is, new TypeReference<List<Book>>(){});
fix:
books = mapper.readValue(is, new TypeReference<List<>>(){});

PMD Error: "OnlyOneReturn":
I fundamentally don't believe in this and this is not an actual thing in real SWE development.

RouteController.java:74
fixed error from "OK":
return new ResponseEntity<>("Error occurred when getting all available books", HttpStatus.INTERNAL_SERVER_ERROR);

RouteController.java:
Added "logger" for PMD SystemPrintln violation

MockApiService.java:
Added "logger" for PMD SystemPrintln violation

PMD "Comments too large"
Comments remaining with this flag are those provider from the instructor

PMD "Comments required"
Remaining functions needing comments are instructor-provided

PMD "AvoidCatchingGenericException"
Three left, all work for generic exception catching due to project continuing.

PMD "DataClass"
Does not make sense to make Book a data class as it is not purely a data class

PMD "LawOfDemeter"
Three left, in these cases it makes sense to leave them in order to avoid multithreading

