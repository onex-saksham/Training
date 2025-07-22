import logging

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(levelname)s: %(message)s')

def parse_transactions(raw_data):
    logging.info("Parsing transactions...")
    transactions = []
    parsed_count = 0
    skipped_count = 0
    error_count = 0

    for idx, record in enumerate(raw_data):
        parts = record.split(",")
        try:
            if len(parts) < 4:
                skipped_count += 1
                logging.warning(f"Skipping incomplete record [{idx}]: {record}")
                continue
            transaction = {
                "date": parts[0].strip(),
                "amount": float(parts[1].strip()),
                "type": parts[2].strip().lower(),
                "description": parts[3].strip()
            }
            transactions.append(transaction)
            parsed_count += 1
        except ValueError as e:
            error_count += 1
            logging.error(f"Error parsing record [{idx}]: {record} | {e}")

    logging.info(f"Parsed: {parsed_count}, Skipped: {skipped_count}, Errors: {error_count}")
    return transactions

def calculate_balance(transactions):
    logging.info("Calculating balance...")
    balance = 0
    credit_count = 0
    debit_count = 0
    for txn in transactions:
        if txn["type"] == "credit":
            balance += txn["amount"]
            credit_count += 1
        elif txn["type"] == "debit":
            balance -= txn["amount"]
            debit_count += 1
        else:
            logging.warning(f"Unknown transaction type: {txn['type']}")
    logging.info(f"Total credits: {credit_count}, debits: {debit_count}, final balance: {balance}")
    return round(balance, 2)

def generate_summary(transactions):
    logging.info("Generating summary...")
    credit_count = sum(1 for t in transactions if t["type"] == "credit")
    debit_count = sum(1 for t in transactions if t["type"] == "debit")
    total_credit_amount = sum(t["amount"] for t in transactions if t["type"] == "credit")
    average_credit = total_credit_amount / credit_count if credit_count > 0 else 0

    try:
        largest_txn = max(transactions, key=lambda t: t["amount"])
    except ValueError:
        largest_txn = None
        logging.warning("No transactions to determine the largest.")

    logging.info(f"Summary: Credits={credit_count}, Debits={debit_count}, Avg Credit={average_credit:.2f}")
    return {
        "credits": credit_count,
        "debits": debit_count,
        "average_credit": round(average_credit, 2),
        "largest_txn": largest_txn
    }

def main():
    raw_data = [
        "2025-07-01, 1200, CREDIT, Salary",
        "2025-07-02, 300, debit, Grocery",
        "2025-07-03, , debit, Restaurant",     
        "2025-07-04, 200, DEBIT",              
        "2025-07-05, 400, credit, Freelance, Bonus"
    ]

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
