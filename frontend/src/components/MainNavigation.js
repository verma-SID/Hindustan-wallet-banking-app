import { useNavigate } from "react-router-dom";

export default function MainNavigation() {
  const navigate = useNavigate();
  function loginButtonHandler() {
    navigate("login");
  }
  function signupButtonHandler() {
    navigate("signup");
  }  //Using useNavigate() for navigate on /login and /signup on the Button clicks
  return (
    <>
      <nav
        className="navbar navbar-expand-lg bg-body-tertiary"
        style={{ boxShadow: "5px 2px 8px rgba(15, 14, 14, 0.15)" }}
      >
        <div className="container-fluid">
          <a className="navbar-brand">HindustanPe</a>
          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">
                <a className="nav-link active" aria-current="page">
                  Individual
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link active">Business</a>
              </li>
              <li className="nav-item dropdown">
                <a
                  className="nav-link dropdown-toggle"
                  role="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  Partners
                </a>
                <ul className="dropdown-menu">
                  <li>
                    <a className="dropdown-item">Partner With Us</a>
                  </li>
                  <li>
                    <a className="dropdown-item">Partner Directory</a>
                  </li>
                  <li>
                    <hr className="dropdown-divider"></hr>
                  </li>
                  <li>
                    <a className="dropdown-item">Policies</a>
                  </li>
                </ul>
              </li>
            </ul>
            <div className="d-flex">
              <button
                className="btn btn-outline-success"
                onClick={loginButtonHandler}
              >
                Login
              </button>
              <button data-testid="signup-button"
                className="btn btn-outline-success"
                onClick={signupButtonHandler}
              >
                Signup
              </button>
            </div>
          </div>
        </div>
      </nav>
    </>
  );
}

//Navigation Bar for Main Welcome Page
