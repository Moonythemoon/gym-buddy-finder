import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { TextField, Button, Select, MenuItem, InputLabel, FormControl, Card, CardContent, Typography, Avatar, Box } from '@mui/material';
import "./SearchPage.css";

function SearchPage({ user, onLogout }) {
  const [buddies, setBuddies] = useState([]);
  const [filters, setFilters] = useState({
    age: "",
    gym: "",
    gender: "",
  });
  const [sortOrder, setSortOrder] = useState("asc");
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const fetchLocalPhotos = (userId) => {
    // Check if photos exist at this path
    const photoUrl = `/profile_pictures/${userId}.jpg`;
    return [photoUrl];
  };

  const fetchData = async () => {
    try {
      // Prepare parameters with non-empty values only
      const params = { sortOrder, page };
      if (filters.age) params.age = filters.age;
      if (filters.gym) params.gym = filters.gym;
      if (filters.gender) params.gender = filters.gender;

      console.log("Sending request with params:", params);

      // Highlight: Ensure the correct endpoint is used
      const response = await axios.get("http://localhost:8080/api/v1/profile-search", { params });
      const data = response.data;

      const buddiesWithPhotos = data.map((buddy) => {
        const photos = fetchLocalPhotos(buddy.id);
        return {
          ...buddy,
          profilePictureUrl: photos[0] || '/profile_pictures/default.jpg',
          additionalPhotos: photos.slice(1),
        };
      });

      setBuddies(buddiesWithPhotos);
      setTotalPages(Math.ceil(data.length / 10));
    } catch (error) {
      if (error.response) {
        console.error("Request failed with status code:", error.response.status);
        console.error("Error message:", error.response.data);
      } else {
        console.error("Error message:", error.message);
      }
    }
  };

  useEffect(() => {
    fetchData();
  }, [filters, sortOrder, page]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters({ ...filters, [name]: value });
  };

  const handleSearch = () => {
    setPage(1);
    fetchData();
  };

  const handleAddBuddy = async (buddyId) => {
    try {
      await axios.post(`http://localhost:8080/api/v1/buddies/${buddyId}/add`);
      alert("Buddy added successfully!");
    } catch (error) {
      console.error("Request failed with status code:", error.response?.status);
      console.error("Error message:", error.message);
      alert("Failed to add buddy.");
    }
  };

  return (
    <div>
      <h2>Search Buddies</h2>
      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
        <TextField
          label="Age"
          name="age"
          value={filters.age}
          onChange={handleInputChange}
        />
        <TextField
          label="Gym"
          name="gym"
          value={filters.gym}
          onChange={handleInputChange}
        />
        <TextField
          label="Gender"
          name="gender"
          value={filters.gender}
          onChange={handleInputChange}
        />
        <Button variant="contained" onClick={handleSearch} className="search-button">Search</Button>
        <FormControl>
          <InputLabel>Sort by Age</InputLabel>
          <Select
            name="sortOrder"
            value={sortOrder}
            onChange={(e) => setSortOrder(e.target.value)}
          >
            <MenuItem value="asc">Ascending</MenuItem>
            <MenuItem value="desc">Descending</MenuItem>
          </Select>
        </FormControl>
      </Box>
      <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2, marginTop: 2 }}>
        {buddies.map((buddy) => (
          <Card key={buddy.id} sx={{ display: 'flex', alignItems: 'center', gap: 2 }}>
            <Avatar src={buddy.profilePictureUrl} alt={`${buddy.firstName} ${buddy.lastName}'s profile`} sx={{ width: 100, height: 100 }} />
            <CardContent>
              <Typography variant="h6">
                <Link to={`/profile/${buddy.id}`}>{buddy.firstName} {buddy.lastName}</Link>
              </Typography>
              <Typography>Age: {buddy.age}</Typography>
              <Typography>Gender: {buddy.gender}</Typography>
              <Typography>Location: {buddy.city}, {buddy.country}</Typography>
              <Button variant="outlined" onClick={() => handleAddBuddy(buddy.id)} className="add-buddy-button">Add Buddy</Button>
              <Box sx={{ display: 'flex', gap: 1, marginTop: 1 }}>
                {buddy.additionalPhotos && buddy.additionalPhotos.map((photo, index) => (
                  <Avatar key={index} src={photo} alt={`Additional Photo ${index + 1}`} sx={{ width: 50, height: 50 }} />
                ))}
              </Box>
            </CardContent>
          </Card>
        ))}
      </Box>
      <Box sx={{ display: 'flex', gap: 1, marginTop: 2 }}>
        {Array.from({ length: totalPages }, (_, index) => (
          <Button key={index} variant="contained" onClick={() => setPage(index + 1)} className="pagination-button">
            {index + 1}
          </Button>
        ))}
      </Box>
    </div>
  );
}

export default SearchPage;
