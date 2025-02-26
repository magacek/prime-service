#!/bin/bash

# Colors for output
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${YELLOW}Prime Service Test Script${NC}"
echo "=============================="
echo

# Set the base URL
BASE_URL="http://localhost:4001"
USERNAME="testuser"
PASSWORD="password123"

# Function to make API calls
call_api() {
    local method=$1
    local endpoint=$2
    local data=$3
    local token=$4
    local headers=""

    if [ ! -z "$token" ]; then
        headers="-H \"Authorization: Bearer $token\""
    fi

    if [ ! -z "$data" ]; then
        echo "curl -s -X $method $BASE_URL$endpoint -H \"Content-Type: application/json\" $headers -d '$data'"
    else
        echo "curl -s -X $method $BASE_URL$endpoint $headers"
    fi
}

echo -e "${YELLOW}Step 1: Register a new user${NC}"
register_cmd=$(call_api "POST" "/auth/register" "{\"username\":\"$USERNAME\",\"password\":\"$PASSWORD\"}")
echo "Executing: $register_cmd"
register_response=$(eval $register_cmd)
echo "Response: $register_response"
echo

echo -e "${YELLOW}Step 2: Login with the registered user${NC}"
login_cmd=$(call_api "POST" "/auth/login" "{\"username\":\"$USERNAME\",\"password\":\"$PASSWORD\"}")
echo "Executing: $login_cmd"
token=$(eval $login_cmd)
echo "Token: $token"

if [ -z "$token" ]; then
    echo -e "${RED}Login failed. Exiting.${NC}"
    exit 1
fi
echo

echo -e "${YELLOW}Step 3: Check if 7 is prime (should be true)${NC}"
check_prime_cmd=$(call_api "GET" "/primes/7" "" "$token")
echo "Executing: $check_prime_cmd"
prime_response=$(eval $check_prime_cmd)
echo "Response: $prime_response"
echo

echo -e "${YELLOW}Step 4: Check if 10 is prime (should be false)${NC}"
check_prime_cmd=$(call_api "GET" "/primes/10" "" "$token")
echo "Executing: $check_prime_cmd"
prime_response=$(eval $check_prime_cmd)
echo "Response: $prime_response"
echo

echo -e "${GREEN}Test completed successfully!${NC}"
echo "You can now open the frontend in your browser to test the UI."
echo "Open practicum3/index.html in your browser or serve it using a simple HTTP server." 