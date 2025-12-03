.PHONY: test login get-data post-data get-data-invalid post-data-invalid

test: login get-data post-data get-data-invalid post-data-invalid

login:
	@echo "\n=== POST /auth/login ==="
	@echo 'curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d {"username":"user","password":"password"}'
	@curl -s -X POST http://localhost:8080/auth/login \
		-H "Content-Type: application/json" \
		-d '{"username":"user","password":"password"}' | jq

get-data:
	@echo "\n=== GET /api/data (valid JWT) ==="
	@echo 'curl http://localhost:8080/api/data -H "Authorization: Bearer <TOKEN>"'
	@TOKEN=$$(curl -s -X POST http://localhost:8080/auth/login \
		-H "Content-Type: application/json" \
		-d '{"username":"user","password":"password"}' | jq -r '.token') && \
	curl -s http://localhost:8080/api/data \
		-H "Authorization: Bearer $$TOKEN" | jq

post-data:
	@echo "\n=== POST /api/data (valid JWT) ==="
	@echo 'curl -X POST http://localhost:8080/api/data -H "Authorization: Bearer <TOKEN>" -d {"header":"Test Note","text":"..."}'
	@TOKEN=$$(curl -s -X POST http://localhost:8080/auth/login \
		-H "Content-Type: application/json" \
		-d '{"username":"user","password":"password"}' | jq -r '.token') && \
	curl -s -X POST http://localhost:8080/api/data \
		-H "Content-Type: application/json" \
		-H "Authorization: Bearer $$TOKEN" \
		-d '{"header":"Test Note","text":"This is a test note"}' | jq

get-data-invalid:
	@echo "\n=== GET /api/data (invalid JWT) ==="
	@echo 'curl http://localhost:8080/api/data -H "Authorization: Bearer invalid-token"'
	@curl -s -w "\nHTTP Status: %{http_code}\n" http://localhost:8080/api/data \
		-H "Authorization: Bearer invalid-token"

post-data-invalid:
	@echo "\n=== POST /api/data (invalid JWT) ==="
	@echo 'curl -X POST http://localhost:8080/api/data -H "Authorization: Bearer invalid-token" -d {...}'
	@curl -s -w "\nHTTP Status: %{http_code}\n" -X POST http://localhost:8080/api/data \
		-H "Content-Type: application/json" \
		-H "Authorization: Bearer invalid-token" \
		-d '{"header":"Test","text":"Test"}'
