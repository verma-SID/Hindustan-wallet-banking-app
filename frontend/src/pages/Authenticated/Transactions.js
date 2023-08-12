import { useLoaderData } from "react-router-dom";
import Transaction from "../../components/Transaction";
export default function Transactions() {
  const data = useLoaderData();
  //reverse the Array for showing to sort by most recent
  const reversedTransList = [...data.transactionList].reverse();
  return (
    <div className="container">
      <div style={{ borderBottom: "1px solid black", marginBottom: "1rem" }}>
        <h1 style={{ marginTop: "2rem", marginBottom: "1rem" }}>
          My Transactions
        </h1>
      </div>
      <div className="list-group">
        {reversedTransList.length===0?<p>No Transactions Yet</p>:reversedTransList.map((transaction) => (
          <Transaction
            key={transaction.id}
            amount={transaction.amount}
            date={transaction.date}
            destination={transaction.destination}
            type={transaction.type}
          ></Transaction>
        ))}
      </div>
    </div>
  );
}
export async function loader({ params }) {
  const id = params.id;
  const response = await fetch(`http://localhost:8080/user/${id}/transactions`);
  if (response.status !== 200) {
    //do error handling by throw
    return null;
  } else {
    const resData = await response.json();
    return resData;
  }
}

//My Transactions Page