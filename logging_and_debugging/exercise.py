import logging

logging.basicConfig(level=logging.DEBUG, format='%(levelname)s: %(message)s')

def parse_transactions(raw_data):
    logging.debug("Starting to parse transactions...")
    transactions = []
    for idx, record in enumerate(raw_data):
        logging.debug(f"Raw record [{idx}]: {record}")
        parts = record.split(",")
        try:
            if len(parts) < 4:
                logging.warning(f"Skipping incomplete record: {record}")
                continue
            transaction = {
                "date": parts[0].strip(),
                "amount": float(parts[1].strip()),
                "type": parts[2].strip().lower(), 
                "description": parts[3].strip()
            }
            transactions.append(transaction)
            logging.debug(f"Parsed transaction [{idx}]: {transaction}")
        except ValueError as e:
            logging.error(f"Failed to parse record [{idx}]: {record} | Error: {e}")
    logging.debug(f"Total valid transactions parsed: {len(transactions)}")
    return transactions

def calculate_balance(transactions):
    logging.debug("Starting balance calculation...")
    balance = 0
    for idx, txn in enumerate(transactions):
        logging.debug(f"Processing txn [{idx}]: {txn}")
        if txn["type"] == "credit":
            balance += txn["amount"]
            logging.debug(f"Credited {txn['amount']}, balance now: {balance}")
        elif txn["type"] == "debit":
            balance -= txn["amount"]
            logging.debug(f"Debited {txn['amount']}, balance now: {balance}")
        else:
            logging.warning(f"Unknown transaction type: {txn['type']}")
    logging.info(f"Final calculated balance: {balance}")
    return round(balance, 2)

def generate_summary(transactions):
    logging.debug("Generating summary...")
    credit_count = sum(1 for t in transactions if t["type"] == "credit")
    debit_count = sum(1 for t in transactions if t["type"] == "debit")
    total_credit_amount = sum(t["amount"] for t in transactions if t["type"] == "credit")

    average_credit = total_credit_amount / credit_count if credit_count > 0 else 0

    try:
        largest_txn = max(transactions, key=lambda t: t["amount"])
        logging.debug(f"Largest transaction: {largest_txn}")
    except ValueError:
        largest_txn = None
        logging.warning("No valid transactions to determine largest.")

    summary = {
        "credits": credit_count,
        "debits": debit_count,
        "average_credit": round(average_credit, 2),
        "largest_txn": largest_txn
    }
    logging.debug(f"Summary generated: {summary}")
    return summary

def main():
    logging.debug("Starting main()...")
    raw_data = [
        "2025-07-01, 1200, CREDIT, Salary",
        "2025-07-02, 300, debit, Grocery",
        "2025-07-03, , debit, Restaurant",     
        "2025-07-04, 200, DEBIT",              
        "2025-07-05, 400, credit, Freelance, Bonus"
    ]

    logging.debug(f"Raw data: {raw_data}")
    transactions = parse_transactions(raw_data)
    balance = calculate_balance(transactions)
    summary = generate_summary(transactions)

    print("\nFinal balance:", balance)
    print("Summary:")
    print("Credits:", summary["credits"])
    print("Debits:", summary["debits"])
    print("Avg Credit:", summary["average_credit"])

    if summary["largest_txn"]:
        print("Largest Txn:", summary["largest_txn"]["description"], "-", summary["largest_txn"]["amount"])
    else:
        print("No valid largest transaction found.")

if __name__ == "__main__":
    main()
