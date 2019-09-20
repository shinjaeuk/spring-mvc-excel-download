# Run

```bash
gradlew bootRun
```

# Download Excel

```bash
curl -o users.xlsx http://127.0.0.1:8080/excel/ 
```

# Reference

- Http Header
  - Content-Disposition: attachment; filename=report.xlsx
    - https://www.ietf.org/rfc/rfc2183.txt
    - https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Disposition
  - Content-Type: application/vnd.ms-excel
    - https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Content-Type