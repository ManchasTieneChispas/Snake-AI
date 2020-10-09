const express = require("express");
const app = express();
var path = require("path");

app.get("/", function (req, res) {
  res.sendFile(path.join(__dirname + "/snake.html"));
});

const port = process.env.PORT || 8080;
app.listen(port, () => console.log(`Server running on port ${port} !`));
