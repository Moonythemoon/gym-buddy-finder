import React, { useState, useEffect, useContext } from "react";
import {
  BrowserRouter as Router,
  Link,
  Routes,
  Route,
  useLocation,
} from "react-router-dom";
import "./App.css";

import LoginPage from "./components/LoginPage/LoginPage";
import HomePage from "./components/HomePage/HomePage";
import RegistrationPage from "./components/RegistrationPage/RegistrationPage";
import { UserContext } from "./UserContext";
import ProfilePage from "./components/ProfilePage/ProfilePage";

import SearchPage from "./components/SearchPage/SearchPage";
import Navbar from "./components/Navbar/NavBar";

function App() {
  const [user, setUser] = useState(localStorage.getItem("user"));
  const handleLogin = (userData) => {
    setUser(userData);
    window.localStorage.setItem("user", JSON.stringify(userData));
  };

  const handleLogout = () => {
    setUser("");
    window.localStorage.setItem("user", "");
  };

  return (
    <Router>
      <div className="App">
        <UserContext.Provider value={{ user, setUser }}>
        <RouteNavBar user={user} onLogout={handleLogout} />
          <Routes>
            <Route
              path="/"
              element={
                !user ? (
                  <LoginPage onLogin={handleLogin} />
                ) : (
                  <HomePage onLogout={handleLogout} />
                )
              }
            />
            <Route path="/register" element={<RegistrationPage />} />
            <Route
              path="/search"
              element={<SearchPage user={user} onLogout={handleLogout} />} 
            />
            
            <Route 
            path="/profile/:id" 
            element={<ProfilePage onLogout={handleLogout} />} 
          />
          </Routes>
          {/* <div>
          {!user && (
            <AuthLink />
          )}
        </div> */}
        </UserContext.Provider>
      </div>
    </Router>
  );
}

function RouteNavBar({ user, onLogout }) {
  const location = useLocation();

  // Highlighted Fix: Conditional rendering of Navbar
  if (location.pathname !== "/register" && location.pathname !== "/") {
    return <Navbar user={user} onLogout={onLogout} />;
  }

  return null;
}
function AuthLink() {
  const location = useLocation();
  if (location.pathname !== "/register") {
    return (
      <p className="registration-link">
        Don't have an account?{" "}
        <Link to="/register">Click here to register</Link>
      </p>
    );
  }
  return null;
}

export default App;
