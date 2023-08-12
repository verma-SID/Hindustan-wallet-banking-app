export default function Balance(props) {
  return (
    <div
      className="card amount"
      style={{ boxShadow: "5px 2px 8px rgba(15, 14, 14, 0.20)" }}
    >
      <div className="card-body">
        <h1>
          <span>&#8377;</span>{props.amount.toFixed(2)}
        </h1>
        <p>Available</p>
      </div>
    </div>
  );
}

//This is the Current Balance Card Component