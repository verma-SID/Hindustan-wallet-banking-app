export default function Card(props) {
  return (
    <div className="card" style={{ backgroundColor: "#f2f4f7" }}>
      <div className="card-body">{props.children}</div>
    </div>
  );
}

//This is Card Component (Wrapper)
