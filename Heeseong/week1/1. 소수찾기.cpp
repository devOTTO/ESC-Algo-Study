#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <set>
#define MAX 9999999999

using namespace std;

bool isPrime(int number)
{
    if (number == 1)
        return false;
    if (number == 2)
        return true;
    if (number % 2 == 0)
        return false;

    bool isPrime = true;
    for (int i = 2; i <= sqrt(number); i++)
    {
        if (number % i == 0) return false;
    }

    return isPrime;
}


int solution(string numbers) {
    int answer = 0;
    sort(numbers.begin(), numbers.end(), greater<int>());
    cout << numbers << endl;
    vector<bool> prime(stoi(numbers) + 1);

    prime[0] = false;
    for (long long i = 1; i < prime.size(); i++)
    {
        prime[i] = isPrime(i);
    }

    string s, sub;

    s = numbers;

    sort(s.begin(), s.end());
    set<int> primes;
    do {
        for (int i = 1; i <= s.size(); i++)
        {
            sub = s.substr(0, i);
            if (prime[stoi(sub)])
            {
                primes.insert(stoi(sub));
            }
        }
    } while (next_permutation(s.begin(), s.end()));

    answer = primes.size();
    return answer;
}
