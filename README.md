# Tamms2

Tamms2 (Title and Media Management Software) is a remake of the point of sale system used by Vintage Stock, Movie Trading Company,  and Entertainmart.  The current software was developed in 1981 by an internal team and is still in use today at all stores in the company.
The projects intent was to study the use of relational databases in Java and SQL, as well as how to design and manage a Database using mySQL to interact with the data.  The project was completed under the restriction of only handling cash transactions, without till balancing (IE, verifying the amount of money in a specific register nightly).
SKUs and UPCs
Tamms2 uses a 9-digit SKU system to track inventory items.  Each item has a seperate set of information for new and used items.  THe nine digit sku is followed by a N or U to denote New or Used product.  Notable exceptions are six demonstration items (100001-100006) and the bulk item codes (beginning with 991001).
 Tamms2 has four primary features:
1) Buy Transactions - Buy transactions use UPC codes (any variation from 10 to 14 digits) to purchase or recieve goods from a customer.  This is the primary method that MTC/VStock uses to obtain new products for their stores.  There are physical rules in place requiring associates to use only UPCs for entries during buys, however the system will attempt a SKU lookup of an item should the UPC not be found.
2) Sell Transactions - Sell transactions allow an associate to sell product to customers.  There is a physical rule requiring associates to follow each sku with a N or U to denote if the item is New or Used.  Items are verified for in-stock and valid SKU numbers during this process by the program.
3) Search - Search the tamms database for items by Title or Author.
4) Add Items - Allows the user to add items with a valid UPC code.
