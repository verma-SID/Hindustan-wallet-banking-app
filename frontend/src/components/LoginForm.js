import { Form, useActionData, useNavigate, useNavigation } from "react-router-dom";
export default function LoginForm() {
  const data = useActionData(); // Using action data to display the error messages on unsuccessful login
  const navigate = useNavigate();
  const navigation=useNavigation();
  function onSignupClickHandler() {
    navigate("/signup");
  }
  return (
    <Form method="post">
      <div class="mb-3">
        <label for="exampleInputEmail1" class="form-label">
          Email Address
        </label>
        <input
          type="email"
          class="form-control"
          id="exampleInputEmail1"
          name="email"
          placeholder="Email"
          required
        ></input>
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label">
          Password
        </label>
        <input
          type="password"
          class="form-control"
          id="exampleInputPassword1"
          name="password"
          placeholder="Password"
          required
        ></input>
      </div>
      <div class="mb-3 form-check">
        <input
          type="checkbox"
          class="form-check-input"
          id="exampleCheck1"
        ></input>
        <label class="form-check-label" for="exampleCheck1">
          Remember Me
        </label>
      </div>
      {data && data.message && (
        <div id="emailHelp" class="form-text">
          {data.message}
        </div>
      )}
      <button class="btn btn-primary">{navigation.state==="submitting"?"Logging In...":"Login"}</button>
      <button class="btn btn-primary" onClick={onSignupClickHandler}>
        Signup
      </button>
    </Form>
  );
}

//Login Form Component