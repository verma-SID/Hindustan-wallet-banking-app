export default function Transaction(props) {
  let color;

  //Set color of the transaction amount to green and red based on type
  if (props.type === "Credit") {
    color = "green";
  } else {
    color = "red";
  }
  return (
    <a className="list-group-item list-group-item-action">
      <div className="d-flex w-100 justify-content-between">
        <h5 className="mb-1" style={{ color: color }}>
          <span>&#8377;</span> {props.amount.toFixed(2)}
        </h5>
        <small className="text-body-secondary">{props.date}</small>
      </div>
      <p className="mb-1">{props.destination}</p>
      <small className="text-body-secondary">{props.type}</small>
    </a>
  );
}

//Recieving props from Transactions.js (Transactions page)
//Transaction Component