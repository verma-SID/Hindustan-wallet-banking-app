import { redirect, useActionData } from "react-router-dom";
import SignupForm from "../components/SignupForm";
import Card from "../components/UI/Card";

export default function Signup() {
  const data=useActionData();
  return (
    <div className="login-content">
      <Card>
        <h1>Signup</h1>
        <SignupForm></SignupForm>
      </Card>
      {data && data.message && <div class="form-text">{data.message}</div>}
    </div>
  );
}
export async function action({ request, params }) {
  const data = await request.formData();
  const loginData = {
    firstName: data.get("firstName"),
    lastName: data.get("lastName"),
    email: data.get("email"),
    password: data.get("password"),
  };
  const response = await fetch("http://localhost:8080/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginData),
  });

  if (response.status === 201) {
    window.alert("Successfully Signed In");
    return redirect("/");
  } else {
    setTimeout(() => {
      window.location.reload(true);
    }, 2000);
    return response;
  }
}

//Signup Page