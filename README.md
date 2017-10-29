# StockWatch
## Stock watch lets people to quickly and easily track the current stock markets giving them an option to contemplate on their future investments.
### Features:
1.User can view any companies current stock value by adding the company name/stock symbol of the company to their watch list and keep an eye on the companies stock data.

2.User can just enter one letter while adding the company name, and the app filters the companies based on that letter and displays a list of company names starting on that letter so that user can select one/many companies and add them to his watch list.

3.A simple swipe refresh by the user updates the stock data of the companies.

4.App differentiates companies by green font and red font. If a company stock's price change amount is a positive value entry is in green font and if the stock's price change is negative it is displayed in red font.

5.User can long click on a stock which pop ups a dialog box providing a option to delete the stock from his watchlist.

6.when user clicks on a stock entry, opens a web browser to the Market watch site for the selected stock.

7.If the user tries to add a stock which is already present in his watchlist then a duplicate stock dialog box appears which clearly mentions that this stock/stock symbol is already present.

8.If the user specified stock is not found a dialog box appears mentioning the same to the user.

9.App has the functionality to check the internet connection of the user and all the above functionalities cannot be performed without internet connection.On start of the application if user has no internet connection or user lost his internet connection in the middle of the activity then a dialog box appears mentioning to check the internet connection.

10.I have implemented the project in Android studio using Java

11.Stock data is pulled using stocksearchapi and parsing the json results.

12.Selected stock symbols and the related names are stored in device's SQLite Database.

### App Uses:
Internet, RecyclerView, Option-Menus, Multiple AsyncTasks,JSON Data, Swipe-Refresh, Dialogs, SQLite Database

### Languages:
Java
