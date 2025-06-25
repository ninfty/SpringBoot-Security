🌐 Request Examples

GET /public

GET /private

GET /admin

# Groups

## Create group
POST /groups
```JSON
{
	"name": "HR_ASSISTANT"
}
```

## List groups
GET /groups


# Users

## Create user
POST /users
```JSON
{
	"user": {
		"name": "assistant",
		"login": "assistant",
		"password": "123"
	},
	"permissions": [
		"HR_ASSISTANT"
	]
}
```
