import { redirect } from "react-router-dom";
import LoginForm from "../components/LoginForm";
import Card from "../components/UI/Card";
export default function LoginPage() {
  return (
    <div className="login-content">
      <Card>
        <h1>Login</h1>
        <LoginForm></LoginForm>
      </Card>
    </div>
  );
}
export async function action({ request, params }) {
  const data = await request.formData();
  const loginData = {
    email: data.get("email"),
    password: data.get("password"),
  };
  console.log(loginData);
  const response = await fetch("http://localhost:8080/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginData),
  });

  if (response.status === 200) {
    localStorage.setItem("userMail", loginData.email);
    return redirect(`/user/${loginData.email}`);
  } else {
    window.alert("Wrong Password or Server Error");
    return redirect("/");
  }
}

//Login page