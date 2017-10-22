# StockWatch
Stock watch lets people to quickly and easily track the current stock markets giving them an option to contemplate on their future investments.
Features:
User can view any companies current stock value by adding the company name/stock symbol of the company to their watch list and keep an eye on the companies stock data.
User can just enter one letter while adding the company name, and the app filters the companies based on that letter and displays a list of company names starting on that letter so that user can select one/many companies and add them to his watch list.
A simple swipe refresh by the user updates the stock data of the companies.
App differentiates companies by green font and red font. If a company stock's price change amount is a positive value entry is in green font and if the stock's price change is negative it is displayed in red font.
User can long click on a stock which pop ups a dialog box providing a option to delete the stock from his watchlist.
when user clicks on a stock entry, opens a web browser to the Market watch site for the selected stock.
If the user tries to add a stock which is already present in his watchlist then a duplicate stock dialog box appears which clearly mentions that this stock/stock symbol is already present.
If the user specified stock is not found a dialog box appears mentioning the same to the user.
App has the functionality to check the internet connection of the user and all the above functionalities cannot be performed without internet connection.On start of the application if user has no internet connection or user lost his internet connection in the middle of the activity then a dialog box appears mentioning to check the internet connection.
I have implemented the project in Android studio using Java
Stock data is pulled using stocksearchapi and parsing the json results.
Selected stock symbols and the related names are stored in device's SQLite Database.
