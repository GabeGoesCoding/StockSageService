import StockSageClient from '../api/stockSageClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class HistoricalData extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'retrieveQueryResultsAndAddToPage', 'updateStocksTable', 'setStatusBar'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("HistoricalData constructor");
    }

    mount() {
        document.getElementById("form-submit-btn").addEventListener("click", this.retrieveQueryResultsAndAddToPage);

        this.header.addHeaderToPage();
        this.client = new StockSageClient();
    }

    setStatusBar() {
        var path = window.location.pathname;

        var statusItems = document.getElementsByClassName('status-item');

        for (var i = 0; i < statusItems.length; i++) {
        var statusItem = statusItems[i];

        var href = statusItem.getAttribute('href');
            if (path.includes(href)) {
                statusItem.classList.add('active');
            }
        }
    }

    async retrieveQueryResultsAndAddToPage(event) {
        // // prevent page refresh
        // event.preventDefault();

        // button set to 'Loading...' when clicked
        const button = document.getElementById('form-submit-btn');
        button.innerText = 'Loading...';

        // get username and fields for createQuery request
        const username = (await this.client.authenticator.getCurrentUserInfo()).email
        console.log(username);
        var startDate = document.getElementById("start-date").value;
        console.log(startDate);
        var endDate = document.getElementById("end-date").value;
        console.log(endDate);
        var aggregation = document.getElementById("aggregation-period").value;
        console.log(aggregation);
        var stockSymbol = document.getElementById("stock-symbol").value;
        console.log(stockSymbol);
    

        // submit fields to createQuery api
        const stockList = await this.client.createQuery(username, startDate, endDate, aggregation, stockSymbol);

        // store stockList in dataStore
        this.dataStore.set('stockList', stockList);

        //button text reverted
        button.innerHTML = "Apply";

        // render stocks table and add to page
        this.updateStocksTable(stockList);
    }

    async updateStocksTable(stocks) {
        // Get the table body element
        var tableBody = document.querySelector("#stock-table tbody");

        // Loop through the stockDataList and create table rows
        for (var i = 0; i < stocks.length; i++) {
        var stock = stocks[i];
        var newRow = tableBody.insertRow();

        // Create table cells for each property in the object
        var cell1 = newRow.insertCell();
        cell1.textContent = stock.date;
        var cell2 = newRow.insertCell();
        cell2.textContent = Number(stock.open).toFixed(2);
        var cell3 = newRow.insertCell();
        cell3.textContent = Number(stock.high).toFixed(2);
        var cell4 = newRow.insertCell();
        cell4.textContent = Number(stock.low).toFixed(2);
        var cell5 = newRow.insertCell();
        cell5.textContent = Number(stock.close).toFixed(2);
        var cell6 = newRow.insertCell();
        cell6.textContent = Number(stock.volume).toLocaleString();
    }
}

}

const main = async () => {
    const historicalData = new HistoricalData();
    historicalData.mount();
    historicalData.setStatusBar();
};

window.addEventListener('DOMContentLoaded', main);
